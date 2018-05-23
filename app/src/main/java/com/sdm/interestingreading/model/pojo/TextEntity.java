package com.sdm.interestingreading.model.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shidongming on 18-2-10.
 */

public class TextEntity {

    @SerializedName("id")
    private String joke_id;
    private String type;
    @SerializedName("text")
    private String joke_content;
    @SerializedName("user_id")
    private String user_id;
    @SerializedName("name")
    private String userName;
    @SerializedName("profile_image")
    private String userIcon;
    @SerializedName("passtime")
    private String userCreateTime;
    @SerializedName("love")
    private String joke_like_num;
    @SerializedName("hate")
    private String joke_unlike_num;
    @SerializedName("comment")
    private String joke_comment_num;

    public TextEntity() {
    }

    public String getJoke_id() {
        return joke_id;
    }

    public void setJoke_id(String joke_id) {
        this.joke_id = joke_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJoke_content() {
        return joke_content;
    }

    public void setJoke_content(String joke_content) {
        this.joke_content = joke_content;
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

    public String getJoke_like_num() {
        return joke_like_num;
    }

    public void setJoke_like_num(String joke_like_num) {
        this.joke_like_num = joke_like_num;
    }

    public String getJoke_unlike_num() {
        return joke_unlike_num;
    }

    public void setJoke_unlike_num(String joke_unlike_num) {
        this.joke_unlike_num = joke_unlike_num;
    }

    public String getJoke_comment_num() {
        return joke_comment_num;
    }

    public void setJoke_comment_num(String joke_comment_num) {
        this.joke_comment_num = joke_comment_num;
    }

    @Override
    public String toString() {
        return "TextEntity{" +
                "joke_id='" + joke_id + '\'' +
                ", type='" + type + '\'' +
                ", joke_content='" + joke_content + '\'' +
                ", user_id='" + user_id + '\'' +
                ", userName='" + userName + '\'' +
                ", userIcon='" + userIcon + '\'' +
                ", userCreateTime='" + userCreateTime + '\'' +
                ", joke_like_num='" + joke_like_num + '\'' +
                ", joke_unlike_num='" + joke_unlike_num + '\'' +
                ", joke_comment_num='" + joke_comment_num + '\'' +
                '}';
    }
}
