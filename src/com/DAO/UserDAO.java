package com.DAO;

import com.model.Question;
import com.model.QuestionRecord;
import com.model.User;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by FuFangzhou on 2017/6/7.
 */
public class UserDAO {
    public static User getUser(String account, Session session) {
        try {
            User user = (User) session.createQuery(String.format("FROM User WHERE account = '%s'", account)).list().get(0);
            return user;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public static User getUserbyId(Session session, int id) {
        return (User) session.get(User.class, id);
    }

    public static List<QuestionRecord> getRecordList(User user, Session session) {
        List<QuestionRecord> questionRecords = (List<QuestionRecord>) session.createQuery(String.format("FROM QuestionRecord WHERE uid='%d' AND result='%d'", user.getId(), Question.PASS)).list();
        return questionRecords;
    }

    public static List<User> getAllUser(Session session) {
        List users = session.createQuery("FROM User").list();
        return users;
    }
}
