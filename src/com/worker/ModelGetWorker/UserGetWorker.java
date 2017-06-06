package com.worker.ModelGetWorker;

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
        try {
            String account = rq.getParameter("account");
            Session session = SessionOpenner.getInstance().getSession();
            User user = (User) session.createQuery(String.format("FROM User where account='%s'", account)).list().get(0);
            is.setInfos("完成了查询");
            is.setAccount(account);
            is.setEmail(user.getEmail());
            is.setDate(user.getDate().toString());
            is.setStatus(true);
        } catch (IndexOutOfBoundsException e) {
            is.setInfos("因为其他原因，尚未找到该用户");
            is.setAccount(FAILEDSTR);
            is.setEmail(FAILEDSTR);
            is.setDate(FAILEDSTR);
            is.setStatus(false);
        }
        return 0;
    }
}
