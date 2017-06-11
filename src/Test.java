import com.model.User;
import com.tool.SessionOpenner;
import org.hibernate.Session;

/**
 * Created by FuFangzhou on 2017/5/31.
 */
public class Test {
    public static void main(String[] args) {
        SessionOpenner so = SessionOpenner.getInstance();
        Session session = so.getSession();
        User user= (User) session.get(User.class,10);
        System.out.println(user);
        session.close();
        SessionOpenner so2 = SessionOpenner.getInstance();
        session = so2.getSession();
        user = (User) session.get(User.class,3);
        System.out.println(user);
    }
}