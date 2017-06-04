package com.register.logInRegister;

import com.json.Info_Status;
import com.json.Info_Status_Id;
import com.model.user.User;
import com.tool.SessionOpenner;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FuFangzhou on 2017/6/4.
 */
public class LoginRegisterImp1 implements LoginRegister {
    @Override
    public int regist(HttpServletRequest rq, Info_Status is) {
        return 0;
    }

    @Override
    public int regist(HttpServletRequest rq, Info_Status_Id isi) {
        try {
            String account = rq.getParameter("account");
            String password = rq.getParameter("password");
            Session session = SessionOpenner.getInstance().getSession();
            User user = (User) session.createQuery(String.format("FROM User WHERE account = '%s'", account)).list().get(0);
            if (user.getPassword().equals(password)) {
                rq.getSession().setAttribute("account", account);
                rq.getSession().setAttribute("password", password);
                isi.setId(user.getId());
                isi.setStatus(true);
                isi.setInfo("恭喜你登陆成功");
                return 1;
            } else {
                isi.setStatus(false);
                isi.setInfo("你输入的密码错误");
                return 0;
            }
        } catch (EnumConstantNotPresentException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            isi.setStatus(false);
            isi.setInfo("尚未找到该用户");
            return 0;
        }
        return 0;
    }
}
