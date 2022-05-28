package com.example.socialmediaapi.Model.CreatePost;

import com.example.socialmediaapi.Model.PostModel.PostImage;

import java.util.List;

public class NewPost {


    public static String POST_TYPE_IMAGE="image";
    public static String POST_TYPE_TEXT="text";
    public static String POST_TYPE_IMAGEANDTEXT="imageandtext";

    String time;
    String user;
    String postType;
    String videoUrl;
    List<PostImage> postImage;
    String description;
    String _id;
    String likes;
    String comments;
    String createdAt;
    String updatedAt;

    public NewPost(String time, String user, String postType, String videoUrl, List<PostImage> postImage, String description, String _id, String likes, String comments, String createdAt, String updatedAt) {
        this.time = time;
        this.user = user;
        this.postType = postType;
        this.videoUrl = videoUrl;
        this.postImage = postImage;
        this.description = description;
        this._id = _id;
        this.likes = likes;
        this.comments = comments;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
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

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
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
