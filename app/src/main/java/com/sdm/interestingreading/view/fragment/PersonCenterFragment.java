package com.sdm.interestingreading.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.google.gson.Gson;
import com.sdm.interestingreading.R;
import com.sdm.interestingreading.model.pojo.TheUserEntity;
import com.sdm.interestingreading.model.pojo.UserEntity;
import com.sdm.interestingreading.utils.CallBack;
import com.sdm.interestingreading.utils.NetworkRequest;
import com.sdm.interestingreading.view.activity.LoginActivity;
import com.sdm.interestingreading.view.item_view_two;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by WangChang on 2016/5/15.
 */
public class PersonCenterFragment extends Fragment implements CallBack {

    private ImageView blurImageView;
    private ImageView avatarImageView;

    private TextView userName;
    private item_view_two nickName,createTime,emailValue;

    private SharedPreferences preferences;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            TheUserEntity user = (TheUserEntity) msg.obj;
            if (!TextUtils.isEmpty(user.getUser_icon())) {
                Glide.with(getContext()).load(user.getUser_icon())
                        .bitmapTransform(new BlurTransformation(getContext(), 25), new CenterCrop(getContext()))
                        .into(blurImageView);

                Glide.with(getContext()).load(user.getUser_icon())
                        .bitmapTransform(new CropCircleTransformation(getContext()))
                        .into(avatarImageView);
            }
            userName.setText(user.getUser_name());
            nickName.setValue(user.getUser_nickname());
            createTime.setValue(String.valueOf(user.getUser_create_time()));
            emailValue.setValue(user.getUser_email());

            preferences.edit().putInt("userId", user.getUser_id()).commit();
        }
    };

    private static final String URL_USER = "http://111.230.210.79/InterestServer/SelectUserByToken";

    @Override
    public void onResume() {
        super.onResume();

        String token = preferences.getString("token","");
        Log.d("PersonCenter", "onResume: "+token);
        if (!TextUtils.isEmpty(token)) {
            NetworkRequest.getDataByOkhttp(URL_USER+"?token="+token,this, false);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);

        initView(view);
        return view;
    }

    @SuppressLint("WrongConstant")
    private void initView(View view) {
        preferences = getActivity().getSharedPreferences("UserData", Context.MODE_APPEND);

        userName = view.findViewById(R.id.user_name);

        nickName = view.findViewById(R.id.user_nickname);
        createTime = view.findViewById(R.id.user_create_time);
        emailValue = view.findViewById(R.id.user_email);

        blurImageView = view.findViewById(R.id.iv_blur);
        avatarImageView = view.findViewById(R.id.user_icon);
        avatarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent("com.sdm.interest.LoginActivity"));
            }
        });

        Glide.with(this).load(R.drawable.user_icon)
                .bitmapTransform(new BlurTransformation(getContext(), 25), new CenterCrop(getContext()))
                .into(blurImageView);

        Glide.with(this).load(R.drawable.user_icon)
                .bitmapTransform(new CropCircleTransformation(getContext()))
                .into(avatarImageView);
    }

    public static PersonCenterFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        PersonCenterFragment fragment = new PersonCenterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void back(String data, boolean isLogin) {
        Log.d("Person", "back: "+data);
        Gson gson = new Gson();
        TheUserEntity user = gson.fromJson(data, TheUserEntity.class);
        Message message = Message.obtain();
        message.obj = user;
        handler.sendMessage(message);
    }
}
