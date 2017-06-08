package com.worker.ModelGetWorker;

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
        try {

            Session session = SessionOpenner.getInstance().getSession();
            User userA = (User) session.createQuery(String.format("FROM User WHERE account '%s'", accountA)).list().get(0);
            User userB = (User) session.createQuery(String.format("FROM User WHERE account '%s'", accountB)).list().get(0);
            int userAid = userA.getId();
            int userBid = userB.getId();
            FriendChat[] friendChats = getFriendChats(userAid, userBid, session);
            List<Message> messages = getMessages(friendChats, chatIndex, userAid);
            iso.setInfos("OK");
            iso.setObj(messages);
            iso.setStatus(true);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            iso.setInfos("Server Query Error");
            iso.setStatus(false);
            iso.setObj(null);
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

    private FriendChat[] getFriendChats(int userAid, int userBid, Session session) {
        Friend friend = (Friend) session.createQuery(String.format("FROM Friend WHERE userAid='%s' AND userBid='%s' OR userAid='%s' AND userBid='%s'", userAid, userBid, userBid, userAid)).list().get(0);
        return (FriendChat[]) friend.getChats().toArray();
    }
}
