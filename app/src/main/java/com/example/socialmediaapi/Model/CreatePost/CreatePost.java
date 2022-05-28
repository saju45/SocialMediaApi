package com.example.socialmediaapi.Model.CreatePost;

public class CreatePost {

    String message;
    NewPost newPost;


    public CreatePost(String message, NewPost newPost) {
        this.message = message;
        this.newPost = newPost;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NewPost getNewPost() {
        return newPost;
    }

    public void setNewPost(NewPost newPost) {
        this.newPost = newPost;
    }
}
