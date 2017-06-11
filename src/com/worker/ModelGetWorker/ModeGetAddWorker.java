package com.worker.ModelGetWorker;

import com.DAO.FriendDAO;
import com.DAO.UserDAO;
import com.json.Friend_Json;
import com.json.Info_Status_Object;
import com.model.Friend;
import com.model.User;
import com.tool.SessionOpenner;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by FuFangzhou on 2017/6/12.
 */
public class ModeGetAddWorker implements ModelGetWorker {
    @Override
    public int work(HttpServletRequest rq, Object ob) {
        Info_Status_Object iso = (Info_Status_Object) ob;
        String account = rq.getParameter("account");
        String targetaccount = rq.getParameter("targetaccount");
        Session session = SessionOpenner.getInstance().getSession();
        try {
            User user = UserDAO.getUser(account,session);
            FriendDAO.addFriendShip(session, account, targetaccount);
            List<Friend_Json> friendIdListByUser = FriendDAO.getFriendIdListByUser(user, session);
            iso.setInfos("添加成功");
            iso.setStatus(true);
            iso.setObj(friendIdListByUser);
        }catch (Exception e){
            iso.setInfos("添加失败");
            iso.setStatus(false);
            iso.setObj(null);
        }
        return 0;
    }
}
