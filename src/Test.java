import com.DAO.FriendDAO;
import com.DAO.UserDAO;
import com.lib.dbconnector.MysqlConnectionFactory;
import com.model.Friend;
import com.model.FriendChat;
import com.model.User;
import com.tool.SessionOpenner;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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