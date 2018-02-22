package com.sdm.interestingreading.presenter.impl;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sdm.interestingreading.utils.BaseData;
import com.sdm.interestingreading.model.IGetData;
import com.sdm.interestingreading.model.impl.GetDataImpl;
import com.sdm.interestingreading.model.pojo.AudioEntity;
import com.sdm.interestingreading.model.pojo.PictureEntity;
import com.sdm.interestingreading.model.pojo.TextEntity;
import com.sdm.interestingreading.model.pojo.VideoEntity;
import com.sdm.interestingreading.presenter.IGetDataPresenter;
import com.sdm.interestingreading.presenter.MyCallBack;
import com.sdm.interestingreading.utils.ParseUtil;
import com.sdm.interestingreading.utils.UrlJointUtil;
import com.sdm.interestingreading.view.BaseInterface;
import com.sdm.interestingreading.view.IAudioFragment;
import com.sdm.interestingreading.view.IPictureFragment;
import com.sdm.interestingreading.view.ITextFragment;
import com.sdm.interestingreading.view.IVideoFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

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
    public void getTextData(String page){
        Map<String,String> map = new HashMap<>();
        map.put("type","29");
        map.put("page",page);
        iGetData.getTextData(UrlJointUtil.getUrl(BaseData.URL,map),this);
    }

    @Override
    public void getVideoData(String page){
        Map<String,String> map = new HashMap<>();
        map.put("type","41");
        map.put("page",page);
        iGetData.getVideoData(UrlJointUtil.getUrl(BaseData.URL,map),this);
    }

    @Override
    public void getAudioData(String page){
        Map<String,String> map = new HashMap<>();
        map.put("type","31");
        map.put("page",page);
        iGetData.getAudioData(UrlJointUtil.getUrl(BaseData.URL,map),this);
    }

    @Override
    public void getPictureData(String page){
        Map<String,String> map = new HashMap<>();
        map.put("type","10");
        map.put("page",page);
        iGetData.getPictureData(UrlJointUtil.getUrl(BaseData.URL,map),this);
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
                Log.d(TAG, data);
                List<PictureEntity> pictureEntityList = gson.fromJson(ParseUtil.parse(data),new TypeToken<List<PictureEntity>>(){}.getType());
                ((IPictureFragment) i).update(pictureEntityList);
                break;
            default:
                break;
        }
    }
}
