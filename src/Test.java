import com.DAO.QuestionDAO;
import com.model.Question;
import com.model.QuestionRecord;
import com.tool.SessionOpenner;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by FuFangzhou on 2017/5/31.
 */
public class Test {
    public static void main(String[] args) throws SQLException {
        Session session = null;
        session = SessionOpenner.getInstance().getSession();
        try {
            List<Question> question_jsonList = QuestionDAO.getQuestionList(1,session);
            Question question = question_jsonList.get(0);
            System.out.println(question.getRecords());
//            int questionId = question.getId();
//            List<QuestionRecord> recordList = QuestionDAO.getQuestionRecordList(questionId,session);
//            for (int i = 0; i < recordList.size(); i++) {
//                QuestionRecord qr = recordList.get(i);
//                System.out.println(qr);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

}