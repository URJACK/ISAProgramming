package com.model;

import java.util.Set;

/**
 * Created by FuFangzhou on 2017/6/6.
 */
public class Friend {
    private int id;
    private int userAid;
    private int userBid;
    private User userA;
    private User userB;
    private Set<FriendChat> chats;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserAid() {
        return userAid;
    }

    public void setUserAid(int userAid) {
        this.userAid = userAid;
    }

    public int getUserBid() {
        return userBid;
    }

    public void setUserBid(int userBid) {
        this.userBid = userBid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Friend friend = (Friend) o;

        if (id != friend.id) return false;
        if (userAid != friend.userAid) return false;
        if (userBid != friend.userBid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userAid;
        result = 31 * result + userBid;
        return result;
    }

    public User getUserA() {
        return userA;
    }

    public void setUserA(User userA) {
        this.userA = userA;
    }

    public User getUserB() {
        return userB;
    }

    public void setUserB(User userB) {
        this.userB = userB;
    }

    public Set<FriendChat> getChats() {
        return chats;
    }

    public void setChats(Set<FriendChat> chats) {
        this.chats = chats;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", userAid=" + userAid +
                ", userBid=" + userBid +
                ", userA=" + userA +
                ", userB=" + userB +
                '}';
    }
}
