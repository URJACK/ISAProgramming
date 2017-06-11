import com.DAO.FriendDAO;
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
        String userAaccount = "Debug";
        String userBaccount = "Admin";
        FriendDAO.deleteFriendShip(session, userAaccount, userBaccount);
    }

}