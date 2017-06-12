package com.worker.ModelGetWorker;

import com.DAO.UserDAO;
import com.json.Info_Status_User;
import com.model.User;
import com.tool.SessionOpenner;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FuFangzhou on 2017/6/5.
 */
public class UserGetWorker implements ModelGetWorker {

    private static final String FAILEDSTR = "查找用户失败";

    /**
     * @param ob：Class: Info_Status_User
     * @return
     */
    @Override
    public int work(HttpServletRequest rq, Object ob) {
        Info_Status_User is = (Info_Status_User) ob;
        Session session = null;
        try {
            session = SessionOpenner.getInstance().getSession();
            String account = rq.getParameter("account");
            User user = UserDAO.getUser(account, session);
            is.setInfos("完成了查询");
            is.setAccount(account);
            is.setEmail(user.getEmail());
            is.setDate(user.getDate().toString());
            //MORE INFOMATION
            if (user.getMajor() != null)
                is.setMajor(user.getMajor());
            else
                is.setMajor("尚未设置主修");
            if (user.getClazz() != null)
                is.setClazz(user.getClazz());
            else
                is.setClazz(-1);
            if (user.getIntroduce() != null)
                is.setIntroduce(user.getIntroduce());
            else
                is.setIntroduce("尚未设置自我介绍");
            is.setStatus(true);
        } catch (IndexOutOfBoundsException e) {
            is.setInfos("因为其他原因，尚未找到该用户");
            is.setAccount(FAILEDSTR);
            is.setEmail(FAILEDSTR);
            is.setDate(FAILEDSTR);
            is.setStatus(false);
            session.close();
        } finally {
            session.close();
        }
        return 0;
    }
}
