package com.worker.ModelGetWorker;

import com.DAO.FriendDAO;
import com.json.Info_Status;
import com.tool.SessionOpenner;
import com.worker.SettingWorker.SettingWorker;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FuFangzhou on 2017/6/12.
 */
public class SettingAddRequestWorker implements SettingWorker {

    @Override
    public int work(HttpServletRequest rq, Info_Status is) {
        String account = rq.getParameter("account");
        String targetaccount = rq.getParameter("targetaccount");
        Session session = SessionOpenner.getInstance().getSession();
        try {
            FriendDAO.addFriendShipRequest(session, account, targetaccount);
            is.setInfo("发送请求成功");
            is.setStatus(true);
            return 1;
        }catch (Exception e){
            is.setInfo(e.getMessage());
            is.setStatus(false);
            return 0;
        }finally {
            session.close();
        }
    }
}
