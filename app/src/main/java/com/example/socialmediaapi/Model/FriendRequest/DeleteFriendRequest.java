package com.example.socialmediaapi.Model.FriendRequest;

public class DeleteFriendRequest {

    String message;

    public DeleteFriendRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
