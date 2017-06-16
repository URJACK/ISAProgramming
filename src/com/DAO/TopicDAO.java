package com.DAO;

import com.model.Topic;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by FuFangzhou on 2017/6/16.
 */
public class TopicDAO {
    public static List<Topic> getAllTopic(Session session) {
        List list = session.createQuery("FROM Topic ").list();
        return list;
    }

    public static Topic getTopicById(Session session, int id) {
        return (Topic) session.get(Topic.class, id);
    }

    public static List<Topic> getTopicList(int userId, Session session) {
        List<Topic> topics = session.createQuery(String.format("FROM Topic WHERE uid='%d'", userId)).list();
        return topics;
    }
}
