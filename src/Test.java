import com.DAO.TopicDAO;
import com.model.Topic;
import com.tool.SessionOpenner;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by FuFangzhou on 2017/5/31.
 */
public class Test {
    public static void main(String[] args) {
        Session session = SessionOpenner.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Topic topic = TopicDAO.getTopicById(session, 1);
        topic.setUid(3);
        transaction.commit();
        topic = TopicDAO.getTopicById(session,1);
        System.out.println(topic.getUser().getAccount());
    }
}