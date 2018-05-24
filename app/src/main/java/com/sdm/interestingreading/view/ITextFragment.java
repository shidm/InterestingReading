package com.sdm.interestingreading.view;

import com.sdm.interestingreading.model.pojo.TextEntity;

import java.util.List;

/**
 * Created by shidongming on 18-2-22.
 */

public interface ITextFragment extends BaseInterface {
    void update(List<TextEntity> list);
    void showComment(String which, String data_id, String userIcon);
}
