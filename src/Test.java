import com.DAO.QuestionDAO;
import com.model.Question;
import com.tool.SessionOpenner;
import org.hibernate.Session;

import java.sql.SQLException;

/**
 * Created by FuFangzhou on 2017/5/31.
 */
public class Test {
    public static void main(String[] args) throws SQLException {
        Session session = null;
        session = SessionOpenner.getInstance().getSession();
        Question question = QuestionDAO.getQuestion(1, 1, session);
        System.out.println(question.getCases());
    }

}