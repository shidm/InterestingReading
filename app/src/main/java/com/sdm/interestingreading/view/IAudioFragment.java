package com.sdm.interestingreading.view;

import com.sdm.interestingreading.model.pojo.AudioEntity;

import java.util.List;

/**
 * Created by shidongming on 18-2-22.
 */

public interface IAudioFragment extends BaseInterface {
    void update(List<AudioEntity> list);
    void showComment(String which, String data_id, String userIcon);
}
