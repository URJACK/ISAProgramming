import com.DAO.QuestionDAO;
import com.DAO.UserDAO;
import com.model.Question;
import com.model.User;
import com.tool.SessionOpenner;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by FuFangzhou on 2017/5/31.
 */
public class Test {
    public static void main(String[] args) {
        Session session = SessionOpenner.getInstance().getSession();
        List<Question> allQuestion = QuestionDAO.getAllQuestion(session);
        System.out.println(allQuestion);
    }
}