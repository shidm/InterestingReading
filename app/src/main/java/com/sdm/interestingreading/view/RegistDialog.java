package com.sdm.interestingreading.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sdm.interestingreading.R;
import com.sdm.interestingreading.model.pojo.UserEntity;
import com.sdm.interestingreading.utils.CallBack;
import com.sdm.interestingreading.utils.NetworkRequest;

import java.sql.Date;

public class RegistDialog extends Dialog {

    private static final String REGIST_URL = "http://111.230.210.79/InterestServer/UserAdd";
    public RegistDialog(@NonNull Context context, View v, final CallBack callBack) {
        super(context);
        setContentView(v);
        final EditText uName = v.findViewById(R.id.regist_username);
        final EditText uNickName = v.findViewById(R.id.regist_nickname);
        final EditText uPwd = v.findViewById(R.id.regist_pwd);
        final EditText uRePwd = v.findViewById(R.id.regist_rePwd);
        final EditText uEmail = v.findViewById(R.id.regist_email);
        final Button registBtn = v.findViewById(R.id.regist_btn);

        registBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = uName.getText().toString().trim();
                String nickName = uNickName.getText().toString().trim();
                String pwd = uPwd.getText().toString().trim();
                String rePwd = uRePwd.getText().toString().trim();
                String email = uEmail.getText().toString().trim();

                if (TextUtils.isEmpty(pwd) || TextUtils.isEmpty(rePwd)) {
                    Toast.makeText(getContext(), "密码不能为空!", Toast.LENGTH_SHORT).show();
                } else {
                    if (pwd.equals(rePwd)) {
                        Log.d("RegistDialog:", "onClick: ");
                        NetworkRequest.sendData(REGIST_URL,new UserEntity(0,name,pwd,nickName,email,"", new Date(System.currentTimeMillis())),callBack, false);
                    }else
                        Toast.makeText(getContext(), "两次密码不一样!", Toast.LENGTH_SHORT).show();
                }
                cancel();
            }
        });
    }
}
