package com.sdm.interestingreading.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdm.interestingreading.R;
import com.sdm.interestingreading.model.pojo.PictureEntity;
import com.sdm.interestingreading.presenter.impl.GetDataPresenterImpl;
import com.sdm.interestingreading.view.IPictureFragment;

import java.util.List;

/**
 * Created by shidongming on 18-1-31.
 */

public class PictureFragment extends Fragment implements IPictureFragment{

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_picture,container,false);
        new GetDataPresenterImpl(this).getPictureData("1");
        init();
        return view;
    }

    private void init() {

    }

    @Override
    public void update(List<PictureEntity> list) {
        for (PictureEntity p:list){
            System.out.println(p.toString());
        }
    }
}
