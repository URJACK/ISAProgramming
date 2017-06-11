package com.worker.ModelGetWorker;

import com.DAO.FriendDAO;
import com.DAO.UserDAO;
import com.json.Info_Status_Object;
import com.json.Message;
import com.model.Friend;
import com.model.FriendChat;
import com.model.User;
import com.tool.SessionOpenner;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FuFangzhou on 2017/6/8.
 */
public class ChatInfosGetWorker implements ModelGetWorker {
    //A向B发出消息，希望查看和B的聊天记录
    @Override
    public int work(HttpServletRequest rq, Object ob) {
        Info_Status_Object iso = (Info_Status_Object) ob;
        String accountA = rq.getParameter("account");
        String accountB = rq.getParameter("targetaccount");
        int chatIndex = Integer.parseInt(rq.getParameter("chatindex"));
        System.out.println(accountA);
        System.out.println(accountB);
        System.out.println(chatIndex);
        Session session = null;
        User userA;
        User userB;
        try {
            session = SessionOpenner.getInstance().getSession();
            userA = UserDAO.getUser(accountA,session);
            userB = UserDAO.getUser(accountB,session);
        } catch (IndexOutOfBoundsException e) {
            iso.setInfos("Server Query User Error");
            iso.setStatus(false);
            session.close();
            return 0;
        } catch (Exception e) {
            iso.setInfos("Server Query Error Before Friend");
            iso.setStatus(false);
            session.close();
            return 0;
        }
        try {
            int userAid = userA.getId();
            int userBid = userB.getId();
            FriendChat[] friendChats = FriendDAO.getFriendChats(userAid, userBid, session);
            List<Message> messages = getMessages(friendChats, chatIndex, userAid);
            iso.setInfos("OK");
            iso.setObj(messages);
            iso.setStatus(true);
            session.close();
            return 1;
        } catch (IndexOutOfBoundsException e) {
            iso.setInfos("Server Query Friend Error");
            iso.setStatus(false);
            session.close();
            return 0;
        } catch (Exception e) {
            iso.setInfos("Server Query Error");
            iso.setStatus(false);
            session.close();
            return 0;
        }
    }

    private List<Message> getMessages(FriendChat[] friendChats, int chatIndex, int userAId) {
        List<Message> messages = new ArrayList<>(5);
        for (int i = chatIndex; i < friendChats.length; i++) {
            FriendChat fc = friendChats[i];
            Message message = new Message();
            message.setInfo(fc.getContent());
            if (fc.getSenderid() == userAId)
                message.setSelf(true);
            else
                message.setSelf(false);
            messages.add(message);
        }
        return messages;
    }

}
