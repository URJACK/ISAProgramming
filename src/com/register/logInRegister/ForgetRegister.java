package com.register.logInRegister;

import com.json.Info_Status;
import com.model.user.User;
import com.tool.MailSender;
import com.tool.SessionOpenner;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FuFangzhou on 2017/6/1.
 */
public class ForgetRegister implements LoginRegister{

    //0:未找到该用户  1:找到了该用户,并成功发送邮件  2:找到了用户，但是邮件发送失败
    @Override
    public int regist(HttpServletRequest rq, Info_Status is) {
        String account = rq.getParameter("account");
        SessionOpenner so = SessionOpenner.getInstance();
        Session session = so.getSession();
        try {
            User user = (User) session.createQuery(String.format("FROM User WHERE account='%s'",account)).list().get(0);
            String email = user.getEmail();
            String checknumber = MailSender.getCheckNumber(6);
            rq.getSession().setAttribute("checknumber",checknumber);
            MailSender mailSender = new MailSender();
            mailSender.setTitle("ISA Forget Password!");
            mailSender.setContent(String.format("This is The CAPTCHA:\n%s",checknumber));
            mailSender.setReceiver(email);
            mailSender.sendEmail();
            is.setStatus(true);
            is.setInfo("已经成功的发送了邮件");
        }catch (IndexOutOfBoundsException e){
            is.setStatus(false);
            is.setInfo("未能找到该帐号");
            return 0;
        } catch (Exception e) {
            is.setStatus(false);
            is.setInfo("邮件服务器发送失败");
            return 2;
        }
        return 1;
    }

}
