package com.worker.ModelGetWorker;

import com.DAO.FriendDAO;
import com.DAO.UserDAO;
import com.json.Friend_Json;
import com.json.Info_Status_Object;
import com.model.FriendRequest;
import com.model.User;
import com.tool.SessionOpenner;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FuFangzhou on 2017/6/12.
 */
public class RequestGetWorker implements ModelGetWorker {
    @Override
    public int work(HttpServletRequest rq, Object ob) {
        Session session = null;
        Info_Status_Object iso = (Info_Status_Object) ob;
        try {
            session = SessionOpenner.getInstance().getSession();
            String account = rq.getParameter("account");
            System.out.println(String.format("RequestGetWorker + ACCOUNT:%s",account));
            User user = UserDAO.getUser(account, session);
            List<FriendRequest> friendShipRequest = FriendDAO.getFriendShipRequest(session, user.getAccount());
            List<Friend_Json> friend_jsons = getFriendJsonList(friendShipRequest);
            System.out.println(String.format("RequestGetWorker + SIZE:%s",friend_jsons.size()));
            iso.setInfos("查询申请列表成功");
            iso.setObj(friend_jsons);
            iso.setStatus(true);
            return 1;
        } catch (Exception e) {
            iso.setInfos("查询失败");
            iso.setStatus(false);
            return 0;
        } finally {
            if (session != null)
                session.close();
        }
    }

    private List<Friend_Json> getFriendJsonList(List<FriendRequest> friendShipRequest) {
        List<Friend_Json> friend_jsons = new ArrayList<>();
        for (int i = 0; i < friendShipRequest.size(); i++) {
            Friend_Json fj = new Friend_Json();
            FriendRequest fr = friendShipRequest.get(i);
            User sender = fr.getUserA();
            fj.setAccount(sender.getAccount());
            fj.setEmail(sender.getEmail());
            friend_jsons.add(fj);
        }
        return friend_jsons;
    }
}
