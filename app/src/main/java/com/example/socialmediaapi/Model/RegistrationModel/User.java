package com.example.socialmediaapi.Model.RegistrationModel;

public class User {

    String firstName;
    String lastName;
    String email;
    String mobile;
    String password;
    ProfileImage profileImage;
    CoverImage coverImage;

    String roll;
    String _id;
    String createdAt;
    String updatedAt;

    public User(String firstName, String lastName, String email, String _id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this._id = _id;
    }

    public User(String firstName, String lastName, String email, String mobile, String password, ProfileImage profileImage, CoverImage coverImage, String roll, String _id, String createdAt, String updatedAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.profileImage = profileImage;
        this.coverImage = coverImage;
        this.roll = roll;
        this._id = _id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
