package com.example.socialmediaapi.Model.Like;

public class LikeResponse {

    String message;

    public LikeResponse(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
