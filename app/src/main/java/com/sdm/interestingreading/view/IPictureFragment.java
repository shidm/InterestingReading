package com.sdm.interestingreading.view;

import com.sdm.interestingreading.model.pojo.PictureEntity;

import java.util.List;

/**
 * Created by shidongming on 18-2-22.
 */

public interface IPictureFragment extends BaseInterface {
    void update(List<PictureEntity> list);
}
