package com.sdm.interestingreading.model.impl;

import com.sdm.interestingreading.model.BaseData;
import com.sdm.interestingreading.model.IGetData;
import com.sdm.interestingreading.presenter.MyCallBack;
import com.sdm.interestingreading.utils.NetworkRequest;

/**
 * Created by shidongming on 18-2-22.
 */

public class GetDataImpl implements IGetData {

    @Override
    public void getTextData(String url, MyCallBack back) {
        NetworkRequest.getData(url, "段子", back);
    }

    @Override
    public void getVideoData(String url, MyCallBack back) {
        NetworkRequest.getData(url, "视频", back);
    }

    @Override
    public void getAudioData(String url, MyCallBack back) {
        NetworkRequest.getData(url, "声音", back);
    }

    @Override
    public void getPictureData(String url, MyCallBack back) {
        NetworkRequest.getData(url, "图片", back);
    }

}
