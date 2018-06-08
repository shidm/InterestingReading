package com.sdm.interestingreading.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sdm.interestingreading.R;
import com.sdm.interestingreading.model.pojo.MyComment;
import com.sdm.interestingreading.utils.CallBack;
import com.sdm.interestingreading.utils.NetworkRequest;

import java.sql.Date;


public class EditCommentFragment extends Fragment implements CallBack {

    private static String dataId = null;

    private static String fragTag = null;

    private View view;

    private SharedPreferences preferences;

    private ImageView back;
    private TextView send;
    private EditText content;

    private String TAG = "EditComment";

    private static final String PUBLISH_COMMENT_URL = "http://111.230.210.79/InterestServer/CommentAdd";

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String data = (String) msg.obj;
            Toast.makeText(getContext(), data, Toast.LENGTH_SHORT).show();
        }
    };

    public static EditCommentFragment newInstance(String whichModel, String data_id) {
        fragTag = whichModel;
        dataId = data_id;
        return new EditCommentFragment();
    }

    @SuppressLint("WrongConstant")
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit_comment, container, false);

        preferences = getActivity().getSharedPreferences("UserData", Context.MODE_APPEND);

        init();
        return view;
    }

    private void init() {
        back = view.findViewById(R.id.edit_comment_back);
        send = view.findViewById(R.id.edit_comment_send);
        content = view.findViewById(R.id.edit_comment_et);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.remove(CommentFragment.editFragment);
                transaction.commit();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int userId = preferences.getInt("userId",-1);
                Log.d(TAG, "onClick: "+userId);
                if (userId != -1) {
                    MyComment comment = null;
                    switch (fragTag) {
                        case "段子":
                            comment = new MyComment(0,-1, userId,-1,-1, Integer.valueOf(dataId),content.getText().toString().trim(),
                                    0,0,new Date(System.currentTimeMillis()));
                            break;
                        case "图片":
                            comment = new MyComment(0, Integer.valueOf(dataId), userId,-1,-1,-1,content.getText().toString().trim(),
                                    0,0,new Date(System.currentTimeMillis()));
                            break;
                        case "声音":
                            comment = new MyComment(0,-1,userId,-1, Integer.valueOf(dataId), -1,content.getText().toString().trim(),
                                    0,0,new Date(System.currentTimeMillis()));
                            break;
                        case "视频":
                            comment = new MyComment(0,-1,userId, Integer.valueOf(dataId),-1, -1,content.getText().toString().trim(),
                                    0,0,new Date(System.currentTimeMillis()));
                            break;
                        default:
                            break;
                    }
                    NetworkRequest.sendData(PUBLISH_COMMENT_URL, comment, EditCommentFragment.this, false);
                }
            }
        });
    }

    @Override
    public void back(String data, boolean isLogin) {
        Message message = Message.obtain();
        message.obj = data;
        handler.sendMessage(message);
    }
}
