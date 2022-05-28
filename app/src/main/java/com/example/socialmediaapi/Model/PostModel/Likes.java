package com.example.socialmediaapi.Model.PostModel;

public class Likes {

    User user;
    String type;
    String _id;

    public Likes(User user, String type, String _id) {
        this.user = user;
        this.type = type;
        this._id = _id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
