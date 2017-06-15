import com.DAO.QuestionDAO;
import com.tool.SessionOpenner;
import org.hibernate.Session;

/**
 * Created by FuFangzhou on 2017/5/31.
 */
public class Test {
    public static void main(String[] args) {
        Session session = SessionOpenner.getInstance().getSession();

        if(QuestionDAO.hasFinished(1,1,session))
            System.out.println("OK");
        else
            System.out.println("NO");
    }
}