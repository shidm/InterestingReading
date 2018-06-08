package com.sdm.interestingreading.model.pojo;

import java.io.Serializable;
import java.sql.Date;

public class MyComment implements Serializable {

    private static final long serialVersionUID = 1L;

    private int comment_id;
    private int picture_id;
    private int user_id;
    private int video_id;
    private int audio_id;
    private int joke_id;
    private String comment_content;
    private int comment_like;
    private int comment_unlike;
    private Date comment_create_time;

    public MyComment() {
    }

    public MyComment(int comment_id, int picture_id, int user_id, int video_id, int audio_id, int joke_id, String comment_content, int comment_like, int comment_unlike, Date comment_create_time) {
        this.comment_id = comment_id;
        this.picture_id = picture_id;
        this.user_id = user_id;
        this.video_id = video_id;
        this.audio_id = audio_id;
        this.joke_id = joke_id;
        this.comment_content = comment_content;
        this.comment_like = comment_like;
        this.comment_unlike = comment_unlike;
        this.comment_create_time = comment_create_time;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getPicture_id() {
        return picture_id;
    }

    public void setPicture_id(int picture_id) {
        this.picture_id = picture_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    public int getAudio_id() {
        return audio_id;
    }

    public void setAudio_id(int audio_id) {
        this.audio_id = audio_id;
    }

    public int getJoke_id() {
        return joke_id;
    }

    public void setJoke_id(int joke_id) {
        this.joke_id = joke_id;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public int getComment_like() {
        return comment_like;
    }

    public void setComment_like(int comment_like) {
        this.comment_like = comment_like;
    }

    public int getComment_unlike() {
        return comment_unlike;
    }

    public void setComment_unlike(int comment_unlike) {
        this.comment_unlike = comment_unlike;
    }

    public Date getComment_create_time() {
        return comment_create_time;
    }

    public void setComment_create_time(Date comment_create_time) {
        this.comment_create_time = comment_create_time;
    }

    @Override
    public String toString() {
        return "MyComment{" +
                "comment_id=" + comment_id +
                ", picture_id=" + picture_id +
                ", user_id=" + user_id +
                ", video_id=" + video_id +
                ", audio_id=" + audio_id +
                ", joke_id=" + joke_id +
                ", comment_content='" + comment_content + '\'' +
                ", comment_like=" + comment_like +
                ", comment_unlike=" + comment_unlike +
                ", comment_create_time=" + comment_create_time +
                '}';
    }
}
