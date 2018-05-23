package com.sdm.interestingreading.model.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shidongming on 18-2-10.
 */

public class PictureEntity {
    @SerializedName("id")
    private String picture_id;
    private String type;
    @SerializedName("text")
    private String picture_content;
    @SerializedName("user_id")
    private String user_id;
    @SerializedName("name")
    private String userName;
    @SerializedName("profile_image")
    private String userIcon;
    @SerializedName("passtime")
    private String userCreateTime;
    @SerializedName("love")
    private String picture_like_num;
    @SerializedName("hate")
    private String picture_unlike_num;
    @SerializedName("comment")
    private String picture_comment_num;
    @SerializedName("image0")
    private String picture_content_img;
    @SerializedName("is_gif")
    private String is_gif;
    @SerializedName("width")
    private String picture_width;
    @SerializedName("height")
    private String picture_height;

    public PictureEntity() {
    }

    public String getPicture_id() {
        return picture_id;
    }

    public void setPicture_id(String picture_id) {
        this.picture_id = picture_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPicture_content() {
        return picture_content;
    }

    public void setPicture_content(String picture_content) {
        this.picture_content = picture_content;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(String userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public String getPicture_like_num() {
        return picture_like_num;
    }

    public void setPicture_like_num(String picture_like_num) {
        this.picture_like_num = picture_like_num;
    }

    public String getPicture_unlike_num() {
        return picture_unlike_num;
    }

    public void setPicture_unlike_num(String picture_unlike_num) {
        this.picture_unlike_num = picture_unlike_num;
    }

    public String getPicture_comment_num() {
        return picture_comment_num;
    }

    public void setPicture_comment_num(String picture_comment_num) {
        this.picture_comment_num = picture_comment_num;
    }

    public String getPicture_content_img() {
        return picture_content_img;
    }

    public void setPicture_content_img(String picture_content_img) {
        this.picture_content_img = picture_content_img;
    }

    public String getIs_gif() {
        return is_gif;
    }

    public void setIs_gif(String is_gif) {
        this.is_gif = is_gif;
    }

    public String getPicture_width() {
        return picture_width;
    }

    public void setPicture_width(String picture_width) {
        this.picture_width = picture_width;
    }

    public String getPicture_height() {
        return picture_height;
    }

    public void setPicture_height(String picture_height) {
        this.picture_height = picture_height;
    }

    @Override
    public String toString() {
        return "PictureEntity{" +
                "picture_id='" + picture_id + '\'' +
                ", type='" + type + '\'' +
                ", picture_content='" + picture_content + '\'' +
                ", user_id='" + user_id + '\'' +
                ", userName='" + userName + '\'' +
                ", userIcon='" + userIcon + '\'' +
                ", userCreateTime='" + userCreateTime + '\'' +
                ", picture_like_num='" + picture_like_num + '\'' +
                ", picture_unlike_num='" + picture_unlike_num + '\'' +
                ", picture_comment_num='" + picture_comment_num + '\'' +
                ", picture_content_img='" + picture_content_img + '\'' +
                ", is_gif='" + is_gif + '\'' +
                ", picture_width='" + picture_width + '\'' +
                ", picture_height='" + picture_height + '\'' +
                '}';
    }
}
