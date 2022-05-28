package com.example.socialmediaapi.Model;

import com.example.socialmediaapi.Model.RegistrationModel.User;

import java.util.List;

public class AllUsers {

    List<User> users;

    public AllUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
