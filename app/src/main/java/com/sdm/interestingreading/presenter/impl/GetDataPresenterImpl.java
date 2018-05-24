package com.sdm.interestingreading.presenter.impl;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sdm.interestingreading.model.pojo.CommentEntity;
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
import com.sdm.interestingreading.view.ICommentFragment;
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

public class GetDataPresenterImpl implements IGetDataPresenter, MyCallBack {

    private IGetData iGetData;
    private BaseInterface i;

    public GetDataPresenterImpl(BaseInterface i) {
        this.i = i;
        iGetData = new GetDataImpl();
    }

    @Override
    public void getTextData(String page) {
        Map<String, String> map = new HashMap<>();
        map.put("type", "29");
        map.put("page", page);
        iGetData.getTextData(UrlJointUtil.getUrl(BaseData.CONTENT_URL, map), this);
    }

    @Override
    public void getVideoData(String page) {
        Map<String, String> map = new HashMap<>();
        map.put("type", "41");
        map.put("page", page);
        iGetData.getVideoData(UrlJointUtil.getUrl(BaseData.CONTENT_URL, map), this);
    }

    @Override
    public void getAudioData(String page) {
        Map<String, String> map = new HashMap<>();
        map.put("type", "31");
        map.put("page", page);
        iGetData.getAudioData(UrlJointUtil.getUrl(BaseData.CONTENT_URL, map), this);
    }

    @Override
    public void getPictureData(String page) {
        Map<String, String> map = new HashMap<>();
        map.put("type", "10");
        map.put("page", page);
        iGetData.getPictureData(UrlJointUtil.getUrl(BaseData.CONTENT_URL, map), this);
    }

    @Override
    public void getCommentData(String page, String data_id) {
        Map<String, String> map = new HashMap<>();
        map.put("data_id", data_id);
        map.put("page", page);
        iGetData.getCommentData(UrlJointUtil.getUrl(BaseData.COMMENT_URL, map), this);
    }

    @Override
    public void call(String which, String data) {
        Gson gson = new Gson();
        switch (which) {
            case "段子":
                List<TextEntity> textEntityList = gson.fromJson(ParseUtil.parseContent(data), new TypeToken<List<TextEntity>>() {
                }.getType());
                Log.d(TAG, "call: " + textEntityList.size());
                ITextFragment iTextFragment = (ITextFragment) i;

                iTextFragment.update(textEntityList);
                break;
            case "视频":
                List<VideoEntity> videoEntityList = gson.fromJson(ParseUtil.parseContent(data), new TypeToken<List<VideoEntity>>() {
                }.getType());
                IVideoFragment iVideoFragment = (IVideoFragment) i;
                iVideoFragment.update(videoEntityList);
                break;
            case "声音":
                List<AudioEntity> audioEntityList = gson.fromJson(ParseUtil.parseContent(data), new TypeToken<List<AudioEntity>>() {
                }.getType());
                IAudioFragment iAudioFragment = (IAudioFragment) i;
                iAudioFragment.update(audioEntityList);
                break;
            case "图片":
                Log.d(TAG, data);
                List<PictureEntity> pictureEntityList = gson.fromJson(ParseUtil.parseContent(data), new TypeToken<List<PictureEntity>>() {
                }.getType());
                ((IPictureFragment) i).update(pictureEntityList);
                break;
            case "评论":
                String[] strings = ParseUtil.parseComment(data);
                Log.d(TAG, "评论: "+strings[1]);
                List<CommentEntity> commentEntityList = gson.fromJson(strings[1], new TypeToken<List<CommentEntity>>() {
                }.getType());
                ICommentFragment iCommentFragment = (ICommentFragment) i;
                iCommentFragment.update(commentEntityList, strings[0]);
                break;
            default:
                break;
        }
    }
}
