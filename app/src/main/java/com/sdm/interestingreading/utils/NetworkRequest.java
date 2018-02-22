package com.sdm.interestingreading.utils;

import com.sdm.interestingreading.presenter.MyCallBack;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by shidongming on 18-2-22.
 */

public class NetworkRequest {
    public static void getData(String url, final String which, final MyCallBack back){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request =new Request.Builder()
                .url(url)
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //请求失败
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //请求成功
                String data = response.body().string();
                back.call(which,data);
            }
        });
    }
}
