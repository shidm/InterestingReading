package com.sdm.interestingreading.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.sdm.interestingreading.R;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by WangChang on 2016/5/15.
 */
public class PersonCenterFragment extends Fragment {

    private ImageView blurImageView;
    private ImageView avatarImageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);

        blurImageView = view.findViewById(R.id.iv_blur);
        avatarImageView = view.findViewById(R.id.iv_avatar);

        Glide.with(this).load(R.drawable.icon)
                .bitmapTransform(new BlurTransformation(getContext(), 25), new CenterCrop(getContext()))
                .into(blurImageView);

        Glide.with(this).load(R.drawable.icon)
                .bitmapTransform(new CropCircleTransformation(getContext()))
                .into(avatarImageView);
        return view;
    }

    public static PersonCenterFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        PersonCenterFragment fragment = new PersonCenterFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
