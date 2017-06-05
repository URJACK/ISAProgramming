package com.worker.logInWorker;

import com.json.Info_Status;
import com.model.user.User;
import com.tool.SessionOpenner;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FuFangzhou on 2017/6/4.
 */
public class LoginWorkerImp implements LoginWorker {

    @Override
    public int work(HttpServletRequest rq, Info_Status is) {
        try {
            String account = rq.getParameter("account");
            String password = rq.getParameter("password");
            Session session = SessionOpenner.getInstance().getSession();
            User user = (User) session.createQuery(String.format("FROM User WHERE account = '%s'", account)).list().get(0);
            if (user.getPassword().equals(password)) {
                rq.getSession().setAttribute("account", account);
                rq.getSession().setAttribute("password", password);
                rq.getSession().setAttribute("id",user.getId());
                is.setStatus(true);
                is.setInfo("恭喜你登陆成功");
                return 1;
            } else {
                is.setStatus(false);
                is.setInfo("你输入的密码错误");
                return 0;
            }
        } catch (EnumConstantNotPresentException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            is.setStatus(false);
            is.setInfo("尚未找到该用户");
            return 0;
        } catch (Exception e){
            e.printStackTrace();
            is.setInfo("服务器发生了错误");
            is.setStatus(false);
            return 0;
        }
        return 0;
    }
}
