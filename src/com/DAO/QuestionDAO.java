package com.DAO;

import com.json.Question_Json;
import com.model.Question;
import com.model.QuestionRecord;
import com.tool.SessionOpenner;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by FuFangzhou on 2017/6/14.
 */
public class QuestionDAO {

    public static List<Question_Json> getQuestion_JsonList(int target) throws Exception {
        Session session = SessionOpenner.getInstance().getSession();
        List<Question> questions = getQuestionList(target, session);
        if (questions==null)
            throw new Exception("查询不到合适等级的题目");

        session.close();
        return null;
    }

    public static List<Question> getQuestionList(int target, Session session) {
        List<Question> list = session.createQuery(String.format("FROM Question WHERE lv = '%d'", target)).list();
        return list;
    }


    public static List<QuestionRecord> getQuestionRecordList(int questionId, Session session) {
        List<QuestionRecord> list = session.createQuery(String.format("FROM QuestionRecord WHERE qid = '%d'",questionId)).list();
        return list;
    }
}
