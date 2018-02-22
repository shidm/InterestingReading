package com.sdm.interestingreading.presenter.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sdm.interestingreading.model.BaseData;
import com.sdm.interestingreading.model.IGetData;
import com.sdm.interestingreading.model.impl.GetDataImpl;
import com.sdm.interestingreading.model.pojo.AudioEntity;
import com.sdm.interestingreading.model.pojo.PictureEntity;
import com.sdm.interestingreading.model.pojo.TextEntity;
import com.sdm.interestingreading.model.pojo.VideoEntity;
import com.sdm.interestingreading.presenter.IGetDataPresenter;
import com.sdm.interestingreading.presenter.MyCallBack;
import com.sdm.interestingreading.utils.UrlJointUtil;
import com.sdm.interestingreading.view.BaseInterface;
import com.sdm.interestingreading.view.IAudioFragment;
import com.sdm.interestingreading.view.IPictureFragment;
import com.sdm.interestingreading.view.ITextFragment;
import com.sdm.interestingreading.view.IVideoFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shidongming on 18-2-22.
 */

public class GetDataPresenterImpl implements IGetDataPresenter,MyCallBack {

    private IGetData iGetData;
    private BaseInterface i;

    public GetDataPresenterImpl(BaseInterface i) {
        this.i = i;
        iGetData = new GetDataImpl();
    }

    @Override
    public void getTextData(){
        Map<String,String> map = new HashMap<>();
        iGetData.getTextData(UrlJointUtil.getUrl(BaseData.TEXT_URL,map),this);
    }

    @Override
    public void getVideoData(){
        Map<String,String> map = new HashMap<>();
        iGetData.getVideoData(UrlJointUtil.getUrl(BaseData.VIDEO_URL,map),this);
    }

    @Override
    public void getAudioData(){
        Map<String,String> map = new HashMap<>();
        iGetData.getAudioData(UrlJointUtil.getUrl(BaseData.AUDIO_URL,map),this);
    }

    @Override
    public void getPictureData(){
        Map<String,String> map = new HashMap<>();
        iGetData.getPictureData(UrlJointUtil.getUrl(BaseData.PICTURE_URL,map),this);
    }

    @Override
    public void call(String which, String data) {
        Gson gson = new Gson();
        switch (which) {
            case "段子":
                List<TextEntity> textEntityList = gson.fromJson(data,new TypeToken<List<TextEntity>>(){}.getType());
                ITextFragment iTextFragment = (ITextFragment) i;

                break;
            case "视频":
                List<VideoEntity> videoEntityList = gson.fromJson(data,new TypeToken<List<VideoEntity>>(){}.getType());
                IVideoFragment iVideoFragment = (IVideoFragment) i;

                break;
            case "声音":
                List<AudioEntity> audioEntityList = gson.fromJson(data,new TypeToken<List<AudioEntity>>(){}.getType());
                IAudioFragment iAudioFragment = (IAudioFragment) i;

                break;
            case "图片":
                List<PictureEntity> pictureEntityList = gson.fromJson(data,new TypeToken<List<PictureEntity>>(){}.getType());
                IPictureFragment iPictureFragment = (IPictureFragment) i;

                break;
            default:
                break;
        }
    }
}
