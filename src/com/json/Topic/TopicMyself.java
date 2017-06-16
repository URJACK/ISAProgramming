package com.json.Topic;

/**
 * Created by FuFangzhou on 2017/6/16.
 */
public class TopicMyself {
    String account;
    int create;
    int follow;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getCreate() {
        return create;
    }

    public void setCreate(int create) {
        this.create = create;
    }

    public int getFollow() {
        return follow;
    }

    public void setFollow(int follow) {
        this.follow = follow;
    }

    @Override
    public String toString() {
        return "TopicMyself{" +
                "account='" + account + '\'' +
                ", create=" + create +
                ", follow=" + follow +
                '}';
    }
}
