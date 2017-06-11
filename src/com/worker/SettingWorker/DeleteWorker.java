package com.worker.SettingWorker;

import com.DAO.FriendDAO;
import com.DAO.UserDAO;
import com.json.Info_Status;
import com.model.Friend;
import com.model.FriendChat;
import com.model.User;
import com.tool.SessionOpenner;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FuFangzhou on 2017/6/11.
 */
public class DeleteWorker implements SettingWorker {
    //A需要删除B
    @Override
    public int work(HttpServletRequest rq, Info_Status is) {
        Session session = SessionOpenner.getInstance().getSession();
        String userAaccount = rq.getParameter("account");
        String userBaccount = rq.getParameter("targetaccount");
        User userA = UserDAO.getUser(userAaccount,session);
        User userB = UserDAO.getUser(userBaccount,session);
        FriendChat[] friendChats = FriendDAO.getFriendChats(userA.getId(),userB.getId(),session);
        Friend friend = FriendDAO.getFriend(session,userA.getId(),userB.getId());
        for (int i = 0; i < friendChats.length; i++) {
            session.delete(friendChats[i]);
        }
        session.delete(friend);
        return 1;
    }
}
