package com.example.socialmediaapi.Model.PostModel;

public class ProfileImage{

    String fileName;
    String uri;

    public ProfileImage(String fileName, String uri) {
        this.fileName = fileName;
        this.uri = uri;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
