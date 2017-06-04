package com.register.logInRegister;

import com.json.Info_Status;
import com.model.user.User;
import com.tool.SessionOpenner;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FuFangzhou on 2017/6/2.
 */
public class ForgetRegister_Confirm implements LoginRegister{
    //0:验证码不匹配   2:服务器发生以外错误
    @Override
    public int regist(HttpServletRequest rq, Info_Status is) {
        try {
            String account = rq.getParameter("account");
            String password = rq.getParameter("password");
            String checknumber = rq.getParameter("checknumber");
            System.out.println(password);
            System.out.println("CHECK: "+checknumber);
            System.out.println("CHECK2: "+rq.getSession().getAttribute("checknumber"));
            if (!checknumber.equals(rq.getSession().getAttribute("checknumber"))) {
                is.setInfo("验证码不匹配，请重新输入");
                is.setStatus(false);
                return 0;
            }else{
                Session session = SessionOpenner.getInstance().getSession();
                Transaction tr = session.beginTransaction();
                User user = (User) session.createQuery(String.format("FROM User WHERE account = '%s'",account)).list().get(0);
                user.setPassword(password);
                session.saveOrUpdate(user);
                tr.commit();
                is.setInfo("成功修改了密码");
                is.setStatus(true);
                return 1;
            }
        }catch (Exception e){
            is.setInfo("服务器发生以外错误");
            is.setStatus(false);
            return 2;
        }
    }
}