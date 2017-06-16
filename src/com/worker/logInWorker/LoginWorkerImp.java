package com.worker.logInWorker;

import com.DAO.UserDAO;
import com.controller.Global;
import com.json.Info_Status;
import com.model.User;
import com.tool.SessionOpenner;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by FuFangzhou on 2017/6/4.
 */
public class LoginWorkerImp implements LoginWorker {

    @Override
    public synchronized int work(HttpServletRequest rq, Info_Status is) {
        try {
            String account = rq.getParameter("account");
            String password = rq.getParameter("password");
            Session session = SessionOpenner.getInstance().getSession();
            User user = UserDAO.getUser(account, session);
            if (user.getPassword().equals(password)) {
                removetheLogined(account);
                rq.getSession().setAttribute("account", account);
                rq.getSession().setAttribute("password", password);
                rq.getSession().setAttribute("id", user.getId());
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
        } catch (Exception e) {
            e.printStackTrace();
            is.setInfo("服务器发生了错误");
            is.setStatus(false);
            return 0;
        }
        return 0;
    }

    private void removetheLogined(String account) {
        List<HttpSession> sessions = Global.getInstance().getSessions();
        for (int i = 0; i < sessions.size(); i++) {
            HttpSession session = sessions.get(i);
            String target = (String) session.getAttribute("account");
            if (target==null)
                continue;
            //如果这个帐号已经被登陆了，则清空这个用户的帐号信息
            if (target.equals(account)) {
                session.removeAttribute("account");
            }
        }
    }
}
