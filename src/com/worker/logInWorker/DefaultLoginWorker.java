package com.worker.logInWorker;

import com.DAO.UserDAO;
import com.json.Info_Status;
import com.model.User;
import com.tool.SessionOpenner;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FuFangzhou on 2017/6/4.
 */
public class DefaultLoginWorker implements LoginWorker {

    @Override
    public int work(HttpServletRequest rq, Info_Status is) {
        try {
            String account = (String) rq.getSession().getAttribute("account");
            String password = (String) rq.getSession().getAttribute("password");
            if (account == null || password == null) {
                is.setInfo("尚未登陆");
                is.setStatus(false);
                return 0;
            } else {
                Session session = SessionOpenner.getInstance().getSession();
                User user = UserDAO.getUser(account,session);
                if (password.equals(user.getPassword())) {
                    is.setInfo("登陆成功");
                    is.setStatus(true);
                    return 1;
                } else {
                    is.setInfo("默认登陆失败，密码可能已经被篡改");
                    is.setStatus(false);
                    return 0;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            is.setInfo("服务器发生了错误");
            is.setStatus(false);
            return 0;
        }
    }
}
