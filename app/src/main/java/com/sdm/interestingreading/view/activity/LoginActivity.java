package com.sdm.interestingreading.view.activity;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sdm.interestingreading.R;
import com.sdm.interestingreading.utils.CallBack;
import com.sdm.interestingreading.utils.NetworkRequest;
import com.sdm.interestingreading.view.RegistDialog;

public class LoginActivity extends AppCompatActivity implements OnClickListener, CallBack {

    private static final String LOGIN_URL = "http://111.230.210.79/InterestServer/UserLogin";

    private TextView mBtnLogin;

    private View progress;

    private View mInputLayout;

    private float mWidth, mHeight;

    private LinearLayout mName, mPsw;

    private TextView userName, userPwd;

    private ImageView back;
    private TextView regist;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                final String token = (String) msg.obj;
                postDelayed(new Runnable() {
                    public void run() {
                        recovery(token);
                    }
                }, 2000);
            }else
                Toast.makeText(getApplicationContext(), (String) msg.obj,Toast.LENGTH_SHORT).show();
        }
    };

    private static String TAG = "Login";

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        preferences = getSharedPreferences("UserData", Context.MODE_APPEND);
        editor = preferences.edit();

        initView();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.login_title).findViewById(R.id.login_back);
        regist = findViewById(R.id.login_title).findViewById(R.id.login_regist);
        mBtnLogin = (TextView) findViewById(R.id.main_btn_login);
        progress = findViewById(R.id.layout_progress);
        mInputLayout = findViewById(R.id.input_layout);
        mName = (LinearLayout) findViewById(R.id.input_layout_name);
        mPsw = (LinearLayout) findViewById(R.id.input_layout_psw);

        userName = mInputLayout.findViewById(R.id.userName);
        userPwd = mInputLayout.findViewById(R.id.userPwd);

        mBtnLogin.setOnClickListener(this);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        regist.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog();
            }
        });
    }

    public void createDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.regist_dialog, null);
        RegistDialog dialog = new RegistDialog(this, view, this);
        dialog.show();
    }

    @Override
    public void onClick(View v) {

        String name = userName.getText().toString().trim();
        String pwd = userPwd.getText().toString().trim();
        Log.d(TAG, "onClick: "+name+":"+pwd);
        NetworkRequest.getDataByOkhttp(LOGIN_URL + "?userName=" + name
                + "&pwd=" + pwd,this, true);

        mWidth = mBtnLogin.getMeasuredWidth();
        mHeight = mBtnLogin.getMeasuredHeight();

        mName.setVisibility(View.INVISIBLE);
        mPsw.setVisibility(View.INVISIBLE);

        inputAnimator(mInputLayout, mWidth, mHeight);

    }

    private void inputAnimator(final View view, float w, float h) {


        ValueAnimator animator = ValueAnimator.ofFloat(0, w);
        animator.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();
                params.leftMargin = (int) value;
                params.rightMargin = (int) value;
                view.setLayoutParams(params);
            }
        });
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mInputLayout, "scaleX", 1f, 0.5f);
        set.setDuration(1000);
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        set.playTogether(animator, animator2);
        set.start();
        set.addListener(new AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                progress.setVisibility(View.VISIBLE);
                progressAnimator(progress);
                mInputLayout.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // TODO Auto-generated method stub

            }
        });

    }

    private void progressAnimator(final View view) {
        PropertyValuesHolder animator = PropertyValuesHolder.ofFloat("scaleX", 0.5f, 1f);
        PropertyValuesHolder animator2 = PropertyValuesHolder.ofFloat("scaleY", 0.5f, 1f);
        ObjectAnimator animator3 = ObjectAnimator.ofPropertyValuesHolder(view, animator, animator2);
        animator3.setDuration(1000);
        animator3.setInterpolator(new JellyInterpolator());
        animator3.start();

    }

    /**
     *
     */
    private void recovery(final String token) {
        progress.setVisibility(View.GONE);
        mInputLayout.setVisibility(View.VISIBLE);
        mName.setVisibility(View.VISIBLE);
        mPsw.setVisibility(View.VISIBLE);

        final MarginLayoutParams params = (MarginLayoutParams) mInputLayout.getLayoutParams();
        params.leftMargin = 0;
        params.rightMargin = 0;
        mInputLayout.setLayoutParams(params);


        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mInputLayout, "scaleX", 0.5f, 1f);
        animator2.setDuration(500);
        animator2.setInterpolator(new AccelerateDecelerateInterpolator());
        animator2.start();
        animator2.addListener(new AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                //跳转操作
                Log.d(TAG, "onAnimationEnd: "+token);
                if (!TextUtils.isEmpty(token)) {
                    editor.putString("token",token);
                    editor.putBoolean("isLogin", true);
                    editor.commit();
                    Log.d(TAG, "token: "+preferences.getString("token",""));
//                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    @Override
    public void back(final String data, boolean isLogin) {
        Log.d(TAG, "back: ");
        if (isLogin) {
            Log.d(TAG, "login: ");
            Message message = Message.obtain();
            message.what=0;
            message.obj = data;
            handler.sendMessage(message);
        }else {
            Log.d(TAG, "regist: "+data);
            Message message = Message.obtain();
            message.what=1;
            message.obj = data;
            handler.sendMessage(message);
        }
    }
}
