package com.DAO;

import com.json.Friend_Json;
import com.lib.dbconnector.MysqlConnectionFactory;
import com.model.Friend;
import com.model.FriendChat;
import com.model.FriendRequest;
import com.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FuFangzhou on 2017/6/7.
 */
public class FriendDAO {
    //User 是请求 查询朋友的人
    public static List<Friend_Json> getFriendIdListByUser(User user, Session session) {
        ArrayList<Friend_Json> friend_jsons = new ArrayList<>();
        int userid = user.getId();
        List list = session.createQuery(String.format("FROM Friend WHERE userAid = '%d' OR userBid = '%d'", userid, userid)).list();
        for (int i = 0; i < list.size(); i++) {
            Friend friend = (Friend) list.get(i);
            Friend_Json fj = getOneFriend_Json(userid, friend, session);
            friend_jsons.add(fj);
        }
        return friend_jsons;
    }

    //Userid 是请求查询朋友的人的Id
    private static Friend_Json getOneFriend_Json(int userid, Friend friend, Session session) {
        Friend_Json fj = new Friend_Json();
        int friendid;
        if (friend.getUserAid() != userid) {
            friendid = friend.getUserAid();
        } else
            friendid = friend.getUserBid();
        System.out.println(friendid);
        User user = UserDAO.getUserbyId(session, friendid);
        fj.setAccount(user.getAccount());
        fj.setEmail(user.getEmail());
        return fj;
    }


    public static Friend getFriend(Session session, int userAid, int userBid) {
        return (Friend) session.createQuery(String.format("FROM Friend WHERE userAid='%d' AND userBid='%d' OR userAid='%d' AND userBid='%d'", userAid, userBid, userBid, userAid)).list().get(0);
    }

    public static FriendChat[] getFriendChats(int userAid, int userBid, Session session) {
        Friend friend = FriendDAO.getFriend(session, userAid, userBid);
        List<FriendChat> friendChatList = session.createQuery(String.format("FROM FriendChat WHERE rid='%d' ORDER BY id ASC ", friend.getId())).list();
        FriendChat[] chats = new FriendChat[friendChatList.size()];
        friendChatList.toArray(chats);
        return chats;
    }

    //使用了MysqlConnectionFactory
    public static void deleteFriendShip(Session session, String userAaccount, String userBaccount) throws SQLException {
        User userA = UserDAO.getUser(userAaccount, session);
        User userB = UserDAO.getUser(userBaccount, session);
        if (userA == null || userB == null)
            throw new SQLException("查询未找到");
        Friend friend = FriendDAO.getFriend(session, userA.getId(), userB.getId());
        Connection connection = MysqlConnectionFactory.getConnection();
        Statement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.execute(String.format("DELETE FROM friend_chat WHERE rid = '%d'", friend.getId()));
            statement.execute(String.format("DELETE FROM friend WHERE id ='%d'", friend.getId()));
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                throw e;
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw e;
                }
        }
    }

    public static List<FriendRequest> getFriendShipRequest(Session session, String userAccount) {
        User user = UserDAO.getUser(userAccount, session);
        List<FriendRequest> list = session.createQuery(String.format("FROM FriendRequest WHERE responseId='%d'", user.getId())).list();
        return list;
    }

    //添加请求
    public static void addFriendShipRequest(Session session, String userAaccount, String userBaccount) throws Exception {
        Transaction transaction = session.beginTransaction();
        FriendRequest fr = new FriendRequest();
        if (userAaccount.equals(userBaccount)) {
            throw new Exception("你不能添加自己为好友");
        }
        User requester = UserDAO.getUser(userAaccount, session);
        if (requester == null)
            throw new Exception("请求者未找到");
        fr.setRequestId(requester.getId());
        User responser = UserDAO.getUser(userBaccount, session);
        if (responser == null)
            throw new Exception("未查到该目标用户");
        FriendRequest fr2 = getFriendRequest(session, requester.getId(), responser.getId());
        if (fr2!=null)
            throw new Exception("你已经对该用户发出了查找邀请。");
        fr.setResponseId(responser.getId());
        session.saveOrUpdate(fr);
        transaction.commit();
    }

    private static FriendRequest getFriendRequest(Session session, int userAid, int userBid) {
        try {
            FriendRequest fr = (FriendRequest) session.createQuery(String.format("FROM FriendRequest WHERE requestId = '%d' AND  responseId = '%d'", userAid, userBid)).list().get(0);
            return fr;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    //删除请求
    public static void deleteFriendShipRequest(Session session, String userAaccount, String userBaccount) throws SQLException {
        Transaction transaction = session.beginTransaction();
        User userA = UserDAO.getUser(userAaccount, session);
        User userB = UserDAO.getUser(userBaccount, session);
        if (userA == null || userB == null) {
            throw new SQLException("User未找到");
        }
        int userAid = userA.getId();
        int userBid = userB.getId();
        Connection connection = null;
        try {
            connection = MysqlConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.execute(String.format("DELETE FROM friend_request WHERE requestId = '%d' AND responseId = '%d'", userAid, userBid));
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
