import com.model.Friend;
import com.model.User;
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
        User user = (User) session.createQuery(String.format("FROM User WHERE id='%d'",1)).list().get(0);
        Friend friend = (Friend) session.createQuery(String.format("FROM Friend WHERE id='%d'",1)).list().get(0);
        friend.setUserA(user);
        friend.setUserAid(user.getId());
        System.out.println(friend.getChats().toArray().length);
        System.out.println(friend.getUserA());
        System.out.println(friend.getUserB());
        session.saveOrUpdate(friend);
        tr.commit();
        session.close();
    }
}
