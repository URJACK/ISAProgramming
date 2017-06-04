package com.model.user;

/**
 * Created by FuFangzhou on 2017/6/1.
 */
public class FriendChat {
    private int id;
    private int rid;
    private int senderid;
    private String content;
    private Friend friend;
    private User sender;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getSenderid() {
        return senderid;
    }

    public void setSenderid(int senderid) {
        this.senderid = senderid;
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

        FriendChat that = (FriendChat) o;

        if (id != that.id) return false;
        if (rid != that.rid) return false;
        if (senderid != that.senderid) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + rid;
        result = 31 * result + senderid;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }
}
