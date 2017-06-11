package com.DAO;

import com.json.Friend_Json;
import com.model.Friend;
import com.model.FriendChat;
import com.model.User;
import org.hibernate.Session;

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
        List<FriendChat> friendChatList = session.createQuery(String.format("FROM FriendChat WHERE rid='%d' ORDER BY id ASC ",friend.getId())).list();
        FriendChat[] chats = new FriendChat[friendChatList.size()];
        friendChatList.toArray(chats);
        return chats;
    }
}
