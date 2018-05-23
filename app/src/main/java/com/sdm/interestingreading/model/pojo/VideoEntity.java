package com.sdm.interestingreading.model.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shidongming on 18-2-10.
 */

public class VideoEntity {
    @SerializedName("id")
    private String video_id;
    private String type;
    @SerializedName("text")
    private String video_content;
    @SerializedName("user_id")
    private String user_id;
    @SerializedName("name")
    private String userName;
    @SerializedName("profile_image")
    private String userIcon;
    @SerializedName("passtime")
    private String userCreateTime;
    @SerializedName("love")
    private String video_like_num;
    @SerializedName("hate")
    private String video_unlike_num;
    @SerializedName("comment")
    private String video_comment_num;
    @SerializedName("videouri")
    private String video_content_url;
    @SerializedName("bimageuri")
    private String video_img;

    public VideoEntity() {
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVideo_content() {
        return video_content;
    }

    public void setVideo_content(String video_content) {
        this.video_content = video_content;
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

    public String getVideo_like_num() {
        return video_like_num;
    }

    public void setVideo_like_num(String video_like_num) {
        this.video_like_num = video_like_num;
    }

    public String getVideo_unlike_num() {
        return video_unlike_num;
    }

    public void setVideo_unlike_num(String video_unlike_num) {
        this.video_unlike_num = video_unlike_num;
    }

    public String getVideo_comment_num() {
        return video_comment_num;
    }

    public void setVideo_comment_num(String video_comment_num) {
        this.video_comment_num = video_comment_num;
    }

    public String getVideo_content_url() {
        return video_content_url;
    }

    public void setVideo_content_url(String video_content_url) {
        this.video_content_url = video_content_url;
    }

    public String getVideo_img() {
        return video_img;
    }

    public void setVideo_img(String video_img) {
        this.video_img = video_img;
    }

    @Override
    public String toString() {
        return "VideoEntity{" +
                "video_id='" + video_id + '\'' +
                ", type='" + type + '\'' +
                ", video_content='" + video_content + '\'' +
                ", user_id='" + user_id + '\'' +
                ", userName='" + userName + '\'' +
                ", userIcon='" + userIcon + '\'' +
                ", userCreateTime='" + userCreateTime + '\'' +
                ", video_like_num='" + video_like_num + '\'' +
                ", video_unlike_num='" + video_unlike_num + '\'' +
                ", video_comment_num='" + video_comment_num + '\'' +
                ", video_content_url='" + video_content_url + '\'' +
                ", video_img='" + video_img + '\'' +
                '}';
    }
}
