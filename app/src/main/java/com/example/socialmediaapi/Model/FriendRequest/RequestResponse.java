package com.example.socialmediaapi.Model.FriendRequest;

import java.util.ArrayList;
import java.util.List;

public class RequestResponse {

    String message;
    List<FriendRequests> friendRequests;
    List<MySendFriendRequests> mySendFriendRequests;


    public RequestResponse(String message, List<FriendRequests> friendRequests, List<MySendFriendRequests> mySendFriendRequests) {
        this.message = message;
        this.friendRequests = friendRequests;
        this.mySendFriendRequests = mySendFriendRequests;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FriendRequests> getFriendRequests() {
        return friendRequests;
    }

    public void setFriendRequests(List<FriendRequests> friendRequests) {
        this.friendRequests = friendRequests;
    }

    public List<MySendFriendRequests> getMySendFriendRequests() {
        return mySendFriendRequests;
    }

    public void setMySendFriendRequests(List<MySendFriendRequests> mySendFriendRequests) {
        this.mySendFriendRequests = mySendFriendRequests;
    }
}
