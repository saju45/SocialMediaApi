package com.example.socialmediaapi.Model.FriendRequest;

import java.util.List;

public class MyFriendResponse {

    String message;
    List<Friends> friends;

    public MyFriendResponse(String message, List<Friends> friends) {
        this.message = message;
        this.friends = friends;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Friends> getFriends() {
        return friends;
    }

    public void setFriends(List<Friends> friends) {
        this.friends = friends;
    }
}
