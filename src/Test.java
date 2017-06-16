import com.DAO.UserDAO;
import com.model.User;
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
        User user = UserDAO.getUserbyId(session, 2);
        user.setAccount("FFZ");
        session.saveOrUpdate(user);
        transaction.commit();
    }
}