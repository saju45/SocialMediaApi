package com.example.socialmediaapi.Model.FriendRequest;

public class FriendRequests {

    Sender sender;
    String time;
    String _id;

    public FriendRequests(Sender sender, String time, String _id) {
        this.sender = sender;
        this.time = time;
        this._id = _id;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
