package com.worker.ModelGetWorker;

import com.DAO.FriendDAO;
import com.DAO.UserDAO;
import com.json.Friend_Json;
import com.json.Info_Status_Object;
import com.model.User;
import com.tool.SessionOpenner;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by FuFangzhou on 2017/6/7.
 */
public class FriendGetWorker implements ModelGetWorker {
    @Override
    public int work(HttpServletRequest rq, Object ob) {
        Info_Status_Object iso = (Info_Status_Object) ob;
        String account = rq.getParameter("account");
        Session session = SessionOpenner.getInstance().getSession();
        try {
            User user = UserDAO.getUser(account, session);
            List<Friend_Json> friend_jsons = FriendDAO.getFriendIdListByUser(user,session);
            iso.setInfos("Return Data Successs");
            iso.setObj(friend_jsons);
            iso.setStatus(true);
            return 1;
        }catch (IndexOutOfBoundsException e){
            return 0;
        }
    }

}
