package com.example.socialmediaapi.Model.FriendRequest;

import com.example.socialmediaapi.Model.PostModel.CoverImage;
import com.example.socialmediaapi.Model.RegistrationModel.ProfileImage;

public class Sender {

    ProfileImage profileImage;
    CoverImage coverImage;
    String _id;
    String firstName;
    String lastName;
    String email;
    String mobile;


    public Sender(ProfileImage profileImage, CoverImage coverImage, String _id, String firstName, String lastName, String email, String mobile) {
        this.profileImage = profileImage;
        this.coverImage = coverImage;
        this._id = _id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
    }

    public ProfileImage getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }

    public CoverImage getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(CoverImage coverImage) {
        this.coverImage = coverImage;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
