package com.model;

/**
 * Created by FuFangzhou on 2017/6/6.
 */
public class QuestionCase {
    private int id;
    private int qid;
    private String input;
    private String output;
    private Question question;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionCase that = (QuestionCase) o;

        if (id != that.id) return false;
        if (qid != that.qid) return false;
        if (input != null ? !input.equals(that.input) : that.input != null) return false;
        if (output != null ? !output.equals(that.output) : that.output != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + qid;
        result = 31 * result + (input != null ? input.hashCode() : 0);
        result = 31 * result + (output != null ? output.hashCode() : 0);
        return result;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "QuestionCase{" +
                "id=" + id +
                ", qid=" + qid +
                ", input='" + input + '\'' +
                ", output='" + output + '\'' +
                '}';
    }
}
