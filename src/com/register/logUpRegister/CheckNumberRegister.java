package com.register.logUpRegister;

import com.json.Info_Status;
import com.model.user.User;
import com.tool.SessionOpenner;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;

/**
 * Created by FuFangzhou on 2017/6/1.
 */
public class CheckNumberRegister {
    public boolean check(HttpServletRequest rq, Info_Status is) {
        HttpSession session = rq.getSession();
        String checknumber = (String) session.getAttribute("checknumber");
        String checknumberrq = rq.getParameter("checknumber");
        if (checknumber.equals(checknumberrq)){
            try {
                User user = new User();
                putDataIntoUser(rq, user);
                Session hibersession = SessionOpenner.getInstance().getSession();
                Transaction transaction = hibersession.beginTransaction();
                hibersession.save(user);
                transaction.commit();
                hibersession.close();
                is.setInfo("恭喜！注册成功!");
                is.setStatus(true);
            }catch (Exception e){
                is.setInfo("出现了意外");
                is.setStatus(false);
                e.printStackTrace();
                return false;
            }
            return true;
        }else{
            is.setInfo("验证码输入错误，请重新输入");
            is.setStatus(false);
            return false;
        }
    }

    private void putDataIntoUser(HttpServletRequest rq, User user) {
        user.setAccount(rq.getParameter("account"));
        user.setPassword(rq.getParameter("password"));
        user.setEmail(rq.getParameter("email"));
        user.setDate(Date.valueOf(SessionOpenner.getNowDate()));
    }
}
