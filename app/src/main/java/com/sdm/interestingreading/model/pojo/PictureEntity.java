package com.sdm.interestingreading.model.pojo;

/**
 * Created by shidongming on 18-2-10.
 */

public class PictureEntity {
    private String text;
    private String name;
    private String profile_image;
    private String cdn_img;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getCdn_img() {
        return cdn_img;
    }

    public void setCdn_img(String cdn_img) {
        this.cdn_img = cdn_img;
    }

    @Override
    public String toString() {
        return "PictureEntity{" +
                "text='" + text + '\'' +
                ", name='" + name + '\'' +
                ", profile_image='" + profile_image + '\'' +
                ", cdn_img='" + cdn_img + '\'' +
                '}';
    }
}
