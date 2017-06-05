package com.worker.SettingWorker;

import com.json.Info_Status;
import com.model.user.User;
import com.tool.SessionOpenner;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FuFangzhou on 2017/6/5.
 */
public class PasswordChangeWorker implements SettingWorker{
    @Override
    public int work(HttpServletRequest rq, Info_Status is) {
        try {
            String account = rq.getParameter("account");
            String newpassword = rq.getParameter("newpassword");
            String oldpassword = rq.getParameter("oldpassword");
            Session session = SessionOpenner.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            User user = (User) session.createQuery(String.format("FROM User WHERE account='%s'", account)).list().get(0);
            if(user.getPassword().equals(oldpassword)) {
                user.setPassword(newpassword);
                session.saveOrUpdate(user);
                transaction.commit();
                is.setInfo("你已经成功的修改了密码");
                is.setStatus(true);
            }else {
                is.setInfo("你的旧密码输入不正确");
                is.setStatus(false);
            }
        }catch (Exception e){
            is.setInfo("因为未知的原因，服务器出现了错误");
            is.setStatus(false);
            return 0;
        }
        return 1;
    }
}
