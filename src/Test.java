import com.model.Friend;
import com.tool.SessionOpenner;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by FuFangzhou on 2017/5/31.
 */
public class Test {
    public static void main(String[] args) {
        SessionOpenner so = SessionOpenner.getInstance();
        Session session = so.getSession();
        Transaction tr = session.beginTransaction();
        Friend friend = (Friend) session.createQuery("FROM Friend WHERE userAid='2' OR userBid='2'").list().get(0);
        System.out.println(friend.getChats().toArray().length);
        System.out.println(friend.getUserA());
        System.out.println(friend.getUserB());
        session.saveOrUpdate(friend);
        tr.commit();
        session.close();
    }
}
