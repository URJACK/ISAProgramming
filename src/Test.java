import com.DAO.QuestionDAO;
import com.model.QuestionRecord;
import com.tool.SessionOpenner;
import org.hibernate.Session;

/**
 * Created by FuFangzhou on 2017/5/31.
 */
public class Test {
    public static void main(String[] args) {
        Session session = SessionOpenner.getInstance().getSession();
        QuestionRecord qr = (QuestionRecord) session.get(QuestionRecord.class,1);
        System.out.println(qr.getQuestion());
    }
}