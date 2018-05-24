package com.sdm.interestingreading.model;

import com.sdm.interestingreading.presenter.MyCallBack;

/**
 * Created by shidongming on 18-2-22.
 */

public interface IGetData {
    void getTextData(String url, MyCallBack back);
    void getVideoData(String url, MyCallBack back);
    void getAudioData(String url, MyCallBack back);
    void getPictureData(String url, MyCallBack back);
    void getCommentData(String url, MyCallBack back);
}
