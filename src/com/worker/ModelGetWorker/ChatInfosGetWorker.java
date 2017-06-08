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
        System.out.println(accountA);
        System.out.println(accountB);
        System.out.println(chatIndex);
        Session session;
        User userA;
        User userB;
        try {
            session = SessionOpenner.getInstance().getSession();
            userA = (User) session.createQuery(String.format("FROM User WHERE account='%s'", accountA)).list().get(0);
            userB = (User) session.createQuery(String.format("FROM User WHERE account='%s'", accountB)).list().get(0);
        }catch (IndexOutOfBoundsException e){
            iso.setInfos("Server Query User Error");
            iso.setStatus(false);
            return 0;
        }catch (Exception e){
            iso.setInfos("Server Query Error Before Friend");
            iso.setStatus(false);
            return 0;
        }
        try{
            int userAid = userA.getId();
            int userBid = userB.getId();
            FriendChat[] friendChats = getFriendChats(userAid, userBid, session);
            List<Message> messages = getMessages(friendChats, chatIndex, userAid);
            iso.setInfos("OK");
            iso.setObj(messages);
            iso.setStatus(true);
            return 1;
        } catch (IndexOutOfBoundsException e) {
            iso.setInfos("Server Query Friend Error");
            iso.setStatus(false);
            return 0;
        }catch (Exception e){
            iso.setInfos("Server Query Error");
            iso.setStatus(false);
            return 0;
        }
    }

    private List<Message> getMessages(FriendChat[] friendChats, int chatIndex, int userAId) {
        List<Message> messages = new ArrayList<>(5);
        System.out.println("DEBUG 3");
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
        System.out.println("DEBUG 4");
        return messages;
    }

    private FriendChat[] getFriendChats(int userAid, int userBid, Session session) {
        System.out.println("DEBUG 1");
        Friend friend = (Friend) session.createQuery(String.format("FROM Friend WHERE userAid='%s' AND userBid='%s' OR userAid='%s' AND userBid='%s'", userAid, userBid, userBid, userAid)).list().get(0);
        System.out.println("DEBUG 2");
        FriendChat[] chats = new FriendChat[friend.getChats().size()];
        friend.getChats().toArray(chats);
        return chats;
    }
}
