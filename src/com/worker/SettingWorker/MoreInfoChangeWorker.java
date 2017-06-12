package com.worker.SettingWorker;

import com.json.Info_Status;
import com.model.User;
import com.tool.SessionOpenner;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FuFangzhou on 2017/6/7.
 */
public class MoreInfoChangeWorker implements SettingWorker {
    @Override
    public int work(HttpServletRequest rq, Info_Status is) {
        String account = rq.getParameter("account");
        String introduce = rq.getParameter("introduce");
        String major = rq.getParameter("major");
        int clazz;
        if (!account.equals(rq.getSession().getAttribute("account"))) {
            is.setInfo("ANS");
            is.setStatus(false);
            return 0;
        }
        try {
            clazz = Integer.parseInt(rq.getParameter("clazz"));
        } catch (Exception e) {
            is.setStatus(false);
            is.setInfo("Class设置只能为整数");
            return 3;
        }
        Session session = null;
        try {
            session = SessionOpenner.getInstance().getSession();
            Transaction tr = session.beginTransaction();
            User user = (User) session.createQuery(String.format("FROM User WHERE account='%s'", account)).list().get(0);
            user.setIntroduce(introduce);
            user.setMajor(major);
            user.setClazz(clazz);
            session.saveOrUpdate(user);
            tr.commit();
            is.setInfo("成功的修改了信息");
            is.setStatus(true);
            return 1;
        } catch (Exception e) {
            is.setInfo("修改信息失败，可能是输入有误或者是服务器出错");
            is.setStatus(false);
            return 2;
        } finally {
            if (session != null)
                session.close();
        }
    }
}
