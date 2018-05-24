package com.sdm.interestingreading.view;

import com.sdm.interestingreading.model.pojo.VideoEntity;

import java.util.List;

/**
 * Created by shidongming on 18-2-22.
 */

public interface IVideoFragment extends BaseInterface {
    void update(List<VideoEntity> list);
    void showComment(String which, String data_id, String userIcon);
}
