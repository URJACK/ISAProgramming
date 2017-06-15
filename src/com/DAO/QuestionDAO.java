package com.DAO;

import com.json.Question_Json;
import com.model.Question;
import com.model.QuestionRecord;
import com.tool.SessionOpenner;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by FuFangzhou on 2017/6/14.
 */
public class QuestionDAO {

    /**
     * 由外部调用，返还一个可以前端使用的Question List
     */
    public static List<Question_Json> getQuestion_JsonList(int target) throws Exception {
        Session session = SessionOpenner.getInstance().getSession();
        List<Question_Json> question_jsons = new ArrayList<>();
        List<Question> questions = getQuestionList(target, session);
        if (questions == null)
            throw new Exception("查询不到合适等级的题目");
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            Question_Json qj = new Question_Json();
            qj.setNumber(question.getNumber());
            qj.setTitle(question.getTitle());
            int submitTimes = 0;
            int passTimes = 0;
            QuestionRecord[] questionRecordArray = getQuestionRecordArray(question);
            for (int j = 0; j < questionRecordArray.length; j++) {
                submitTimes++;
                if (questionRecordArray[j].getResult() == Question.PASS)
                    passTimes++;
            }
            qj.setPass(passTimes);
            qj.setSubmit(submitTimes);
            if (submitTimes != 0)
                qj.setPassrate((float) passTimes / submitTimes);
            else
                qj.setPassrate(0);
            question_jsons.add(qj);
        }
        session.close();
        return question_jsons;
    }

    /**
     * 由内部的单个Question调用
     *
     * @return QuestionRecord 的数组
     */
    public static QuestionRecord[] getQuestionRecordArray(Question question) {
        QuestionRecord[] qrs = new QuestionRecord[question.getRecords().size()];
        Set<QuestionRecord> records = question.getRecords();
        records.toArray(qrs);
        return qrs;
    }

    /**
     * 由内部 getQuestion_JsonList() 调用，用来查询符合target 的所有Question
     *
     * @param target  Question的lv
     * @param session 查询使用的session
     * @return
     */
    public static List<Question> getQuestionList(int target, Session session) {
        List<Question> list = session.createQuery(String.format("FROM Question WHERE lv = '%d'", target)).list();
        return list;
    }

    public static Question getQuestion(int index, int level, Session session) {
        Question question = (Question) session.createQuery(String.format("FROM Question WHERE number='%d' AND lv='%d'", index, level)).list().get(0);
        return question;
    }

    //查找该用户是否对于该题已经有了一次正确的提交记录
    public static boolean hasFinished(int userId, int questionId, Session session) {
        try {
            session.createQuery(String.format("FROM QuestionRecord WHERE uid='%d' AND qid='%d' AND result='%d'", userId, questionId, Question.PASS)).list().get(0);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }
}
