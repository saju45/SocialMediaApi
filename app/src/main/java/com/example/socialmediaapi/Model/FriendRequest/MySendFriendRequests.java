package com.example.socialmediaapi.Model.FriendRequest;

public class MySendFriendRequests {

    String _id;
    UserId userId;


    public MySendFriendRequests(String _id, UserId userId) {
        this._id = _id;
        this.userId = userId;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }
}
