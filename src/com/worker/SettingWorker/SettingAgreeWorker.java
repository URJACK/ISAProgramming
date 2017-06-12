package com.worker.SettingWorker;

import com.DAO.FriendDAO;
import com.json.Info_Status;
import com.tool.SessionOpenner;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FuFangzhou on 2017/6/12.
 */
public class SettingAgreeWorker implements SettingWorker {
    //同意
    @Override
    public int work(HttpServletRequest rq, Info_Status is) {
        Session session = SessionOpenner.getInstance().getSession();
        String account = rq.getParameter("account");
        String targetaccount = rq.getParameter("targetaccount");
        boolean isAgree = Boolean.parseBoolean(rq.getParameter("agree"));
        try {
            FriendDAO.deleteFriendShipRequest(session, targetaccount, account);
            if (isAgree) {
                FriendDAO.addFriendShip(session, targetaccount, account);
            }
            is.setInfo("操作完成");
        } catch (Exception e) {
            is.setInfo(e.getMessage());
        } finally {
            is.setStatus(true);
            if (session != null)
                session.close();
        }
        return 1;
    }

}
