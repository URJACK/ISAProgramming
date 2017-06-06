package com.model;

/**
 * Created by FuFangzhou on 2017/6/6.
 */
public class TopicResponse {
    private int id;
    private int tid;
    private int responserid;
    private int index;
    private String content;
    private User responser;
    private Topic topic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getResponserid() {
        return responserid;
    }

    public void setResponserid(int responserid) {
        this.responserid = responserid;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TopicResponse that = (TopicResponse) o;

        if (id != that.id) return false;
        if (tid != that.tid) return false;
        if (responserid != that.responserid) return false;
        if (index != that.index) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + tid;
        result = 31 * result + responserid;
        result = 31 * result + index;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    public User getResponser() {
        return responser;
    }

    public void setResponser(User responser) {
        this.responser = responser;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
