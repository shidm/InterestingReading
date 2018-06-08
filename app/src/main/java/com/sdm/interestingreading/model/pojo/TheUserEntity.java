package com.sdm.interestingreading.model.pojo;

public class TheUserEntity {

    private int user_id;
    private String user_name;
    private String user_password;
    private String user_nickname;
    private String user_email;
    private String user_icon;
    private String user_create_time;

    public TheUserEntity() {
    }

    public TheUserEntity(int user_id, String user_name, String user_password, String user_nickname, String user_email, String user_icon, String user_create_time) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_nickname = user_nickname;
        this.user_email = user_email;
        this.user_icon = user_icon;
        this.user_create_time = user_create_time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_icon() {
        return user_icon;
    }

    public void setUser_icon(String user_icon) {
        this.user_icon = user_icon;
    }

    public String getUser_create_time() {
        return user_create_time;
    }

    public void setUser_create_time(String user_create_time) {
        this.user_create_time = user_create_time;
    }

    @Override
    public String toString() {
        return "TheUserEntity{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_nickname='" + user_nickname + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_icon='" + user_icon + '\'' +
                ", user_create_time='" + user_create_time + '\'' +
                '}';
    }
}
