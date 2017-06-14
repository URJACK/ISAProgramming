package com.model;

import java.sql.Date;

/**
 * Created by FuFangzhou on 2017/6/6.
 */
public class QuestionRecord {
    private int id;
    private int uid;
    private int qid;
    private Date date;
    private int result;
    private Question question;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionRecord that = (QuestionRecord) o;

        if (id != that.id) return false;
        if (uid != that.uid) return false;
        if (qid != that.qid) return false;
        if (result != that.result) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result1 = id;
        result1 = 31 * result1 + uid;
        result1 = 31 * result1 + qid;
        result1 = 31 * result1 + (date != null ? date.hashCode() : 0);
        result1 = 31 * result1 + result;
        return result1;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "QuestionRecord{" +
                "id=" + id +
                ", uid=" + uid +
                ", qid=" + qid +
                ", date=" + date +
                ", result=" + result +
                ", question=" + question +
                ", user=" + user +
                '}';
    }
}
