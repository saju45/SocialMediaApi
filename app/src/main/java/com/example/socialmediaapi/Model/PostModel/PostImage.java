package com.example.socialmediaapi.Model.PostModel;

public class PostImage {

    Image image;
    String _id;

    public PostImage(Image image, String _id) {
        this.image = image;
        this._id = _id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
