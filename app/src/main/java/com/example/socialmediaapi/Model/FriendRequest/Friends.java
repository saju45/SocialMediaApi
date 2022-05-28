package com.example.socialmediaapi.Model.FriendRequest;

import com.example.socialmediaapi.Model.PostModel.User;

public class Friends {

    User user;

    public Friends(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
