package com.model;

import java.util.Set;

/**
 * Created by FuFangzhou on 2017/6/6.
 */
public class Question {
    public static final int PASS = 1000;
    public static final int FAIL = 0;
    private int id;
    private int lv;
    private int number;
    private String title;
    private String content;
    private String tip;
    private Set<QuestionRecord> records;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Set<QuestionRecord> getRecords() {
        return records;
    }

    public void setRecords(Set<QuestionRecord> records) {
        this.records = records;
    }

    public String getTip() {
        if (tip == null)
            return "";
        else
            return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (id != question.id) return false;
        if (lv != question.lv) return false;
        if (number != question.number) return false;
        if (title != null ? !title.equals(question.title) : question.title != null) return false;
        if (content != null ? !content.equals(question.content) : question.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + lv;
        result = 31 * result + number;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", lv=" + lv +
                ", number=" + number +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
