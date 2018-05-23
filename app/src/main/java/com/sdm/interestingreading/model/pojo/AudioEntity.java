package com.sdm.interestingreading.model.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shidongming on 18-2-10.
 */

public class AudioEntity {
    @SerializedName("id")
    private String audio_id;
    private String type;
    @SerializedName("text")
    private String audio_content;
    @SerializedName("user_id")
    private String user_id;
    @SerializedName("name")
    private String userName;
    @SerializedName("profile_image")
    private String userIcon;
    @SerializedName("passtime")
    private String userCreateTime;
    @SerializedName("love")
    private String audio_like_num;
    @SerializedName("hate")
    private String audio_unlike_num;
    @SerializedName("comment")
    private String audio_comment_num;
    @SerializedName("bimageuri")
    private String audio_content_img;
    @SerializedName("voiceuri")
    private String audio_content_url;
    @SerializedName("voicetime")
    private String audio_time;

    public String getAudio_id() {
        return audio_id;
    }

    public void setAudio_id(String audio_id) {
        this.audio_id = audio_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAudio_content() {
        return audio_content;
    }

    public void setAudio_content(String audio_content) {
        this.audio_content = audio_content;
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

    public String getAudio_like_num() {
        return audio_like_num;
    }

    public void setAudio_like_num(String audio_like_num) {
        this.audio_like_num = audio_like_num;
    }

    public String getAudio_unlike_num() {
        return audio_unlike_num;
    }

    public void setAudio_unlike_num(String audio_unlike_num) {
        this.audio_unlike_num = audio_unlike_num;
    }

    public String getAudio_comment_num() {
        return audio_comment_num;
    }

    public void setAudio_comment_num(String audio_comment_num) {
        this.audio_comment_num = audio_comment_num;
    }

    public String getAudio_content_img() {
        return audio_content_img;
    }

    public void setAudio_content_img(String audio_content_img) {
        this.audio_content_img = audio_content_img;
    }

    public String getAudio_content_url() {
        return audio_content_url;
    }

    public void setAudio_content_url(String audio_content_url) {
        this.audio_content_url = audio_content_url;
    }

    public String getAudio_time() {
        return audio_time;
    }

    public void setAudio_time(String audio_time) {
        this.audio_time = audio_time;
    }

    public AudioEntity() {

    }

    @Override
    public String toString() {
        return "AudioEntity{" +
                "audio_id='" + audio_id + '\'' +
                ", type='" + type + '\'' +
                ", audio_content='" + audio_content + '\'' +
                ", user_id='" + user_id + '\'' +
                ", userName='" + userName + '\'' +
                ", userIcon='" + userIcon + '\'' +
                ", userCreateTime='" + userCreateTime + '\'' +
                ", audio_like_num='" + audio_like_num + '\'' +
                ", audio_unlike_num='" + audio_unlike_num + '\'' +
                ", audio_comment_num='" + audio_comment_num + '\'' +
                ", audio_content_img='" + audio_content_img + '\'' +
                ", audio_content_url='" + audio_content_url + '\'' +
                ", audio_time='" + audio_time + '\'' +
                '}';
    }
}
