package com.example.socialmediaapi.Model.PostModel;

import java.util.List;

public class Post {


    String _id;
    String time;

    User user;
    String postType;
    String videoUrl;
    List<PostImage> postImage;
    String description;
  List<Likes> likes;
  List<Comment>comments;
  String createdAt;
  String updatedAt;

    public Post(String _id, String time, User user, String postType, String videoUrl, List<PostImage> postImage, String description, List<Likes> likes, List<Comment> comments, String createdAt, String updatedAt) {
        this._id = _id;
        this.time = time;
        this.user = user;
        this.postType = postType;
        this.videoUrl = videoUrl;
        this.postImage = postImage;
        this.description = description;
        this.likes = likes;
        this.comments = comments;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public List<PostImage> getPostImage() {
        return postImage;
    }

    public void setPostImage(List<PostImage> postImage) {
        this.postImage = postImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Likes> getLikes() {
        return likes;
    }

    public void setLikes(List<Likes> likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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
