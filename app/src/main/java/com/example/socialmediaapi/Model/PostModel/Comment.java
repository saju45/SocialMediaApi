package com.example.socialmediaapi.Model.PostModel;

public class Comment {

    User user;
    String comment;
    String _id;

    public Comment(User user, String comment, String _id) {
        this.user = user;
        this.comment = comment;
        this._id = _id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
