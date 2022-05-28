package com.example.socialmediaapi.Model.Comment;

public class CommentResponse {


    String message;

    public CommentResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
