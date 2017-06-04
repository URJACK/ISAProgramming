package com.register.logUpRegister;

import com.json.Info_Status;
import com.model.user.User;
import com.tool.MailSender;
import com.tool.SessionOpenner;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FuFangzhou on 2017/6/1.
 */
public class LogUpRegisterimp1 implements LogUpRegister {

    @Override
    public int regist(HttpServletRequest rq, User user, Info_Status is) {
        int result = logUpRegistUser(rq, user);
        is.setStatus(false);
        if (result == 0) {
            MailSender mailSender = new MailSender();
            String checknumber = MailSender.getCheckNumber(6);
            mailSender.setTitle("SICNU-ISA Logup_Check");
            mailSender.setContent(String.format("Your Check Number Is:\n%s", checknumber));
            mailSender.setReceiver(rq.getParameter("email"));
            try {
                mailSender.sendEmail();
                is.setStatus(true);
                is.setInfo("已经发送验证码到你的邮件");
                rq.getSession().setAttribute("checknumber", checknumber);
            } catch (Exception e) {
                e.printStackTrace();
                is.setInfo("发送邮件的服务器出现了未知的错误，请稍后注册");
            }
        } else if (result == 1) {
            is.setInfo("你的账户重名了");
        } else if (result == 2) {
            is.setInfo("你的邮箱已经被注册了");
        } else if (result == 3){
            is.setInfo("数据库状态异常");
        }
        return result;
    }

    //注册用户 0:注册成功  1:账户重名  2:邮箱重名  3:数据库异常
    private int logUpRegistUser(HttpServletRequest rq, User user) {
        try {
            SessionOpenner so = SessionOpenner.getInstance();
            Session session = so.getSession();
            Transaction tr = session.beginTransaction();

            String account = rq.getParameter("account");
            String email = rq.getParameter("email");

            int size = session.createQuery(String.format("FROM User WHERE account = '%s'", account)).list().size();
            if (size != 0) {
                return 1;
            }
            size = session.createQuery(String.format("FROM User WHERE email = '%s'", email)).list().size();
            if (size != 0) {
                return 2;
            }
            //如果没有重复的
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return 3;
        }
    }

}
