package com.DAO;

import com.model.User;
import org.hibernate.Session;

/**
 * Created by FuFangzhou on 2017/6/7.
 */
public class UserDAO {
    public static User getUser(String account, Session session) {
        return (User) session.createQuery(String.format("FROM User WHERE account = '%s'", account)).list().get(0);
    }

    public static User getUserbyId(Session session, int id) {
        return (User) session.get(User.class, id);
    }
}
