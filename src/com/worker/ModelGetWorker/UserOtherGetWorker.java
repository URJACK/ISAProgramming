package com.worker.ModelGetWorker;

import com.json.Info_Status_Object;
import com.json.User_Other_Json;
import com.model.User;
import com.tool.SessionOpenner;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FuFangzhou on 2017/6/7.
 */
public class UserOtherGetWorker implements ModelGetWorker {
    @Override
    public int work(HttpServletRequest rq, Object ob) {
        Info_Status_Object iso = (Info_Status_Object) ob;
        String targetAccount = rq.getParameter("account");
        Session session = SessionOpenner.getInstance().getSession();
        User targetUser;
        try {
            targetUser = (User) session.createQuery(String.format("FROM User WHERE account='%s'", targetAccount)).list().get(0);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            iso.setInfos("尚未找到该用户");
            iso.setStatus(false);
            session.close();
            return 0;
        }
        try {
            User_Other_Json uoj = new User_Other_Json();
            uoj.setEmail(targetUser.getEmail());
            uoj.setAccount(targetUser.getAccount());
            uoj.setClazz(targetUser.getClazz());
            uoj.setDate(targetUser.getDate());
            uoj.setMajor(targetUser.getMajor());
            iso.setInfos("Return Data Success!");
            iso.setStatus(true);
            iso.setObj(uoj);
            session.close();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            iso.setInfos("Data Base Error");
            iso.setStatus(false);
            session.close();
            return 0;
        }
    }
}
