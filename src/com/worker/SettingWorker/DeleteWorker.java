package com.worker.SettingWorker;

import com.DAO.FriendDAO;
import com.json.Info_Status;
import com.tool.SessionOpenner;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

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
        try {
            FriendDAO.deleteFriendShip(session,userAaccount,userBaccount);
            is.setInfo("删除成功");
            is.setStatus(true);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            is.setInfo("删除失败");
            is.setStatus(false);
            return 0;
        }finally {
            session.close();
        }
    }
}
