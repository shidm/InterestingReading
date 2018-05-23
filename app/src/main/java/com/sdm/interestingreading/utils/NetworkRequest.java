package com.sdm.interestingreading.utils;

import android.util.Log;

import com.sdm.interestingreading.presenter.MyCallBack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by shidongming on 18-2-22.
 */

public class NetworkRequest {

    private static final String TAG = "NetworkRequest";

    public static void getData(String u, final String which, final MyCallBack back){

        final String theUrl = u;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(theUrl);
                    //得到connection对象。
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //设置请求方式
                    connection.setRequestMethod("GET");
                    //连接
                    connection.connect();
                    //得到响应码
                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        //得到响应流
                        InputStream inputStream = connection.getInputStream();
                        //将响应流转换成字符串
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "gbk"));
                        StringBuilder stringBuilder = new StringBuilder();
                        String s = null;
                        while ((s = reader.readLine()) != null) {
                            stringBuilder.append(s);
                        }
                        Log.d(TAG, "onResponse: "+stringBuilder.toString());
                        back.call(which, stringBuilder.toString());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

//        OkHttpClient okHttpClient = new OkHttpClient();
//
//        Request request =new Request.Builder()
//                .url("http://api.budejie.com/api/api_open.php?a=list&c=data&type=1")
//                .get()
//                .build();
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                //请求失败
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                //请求成功
//                String data = response.body().string();
//                Log.d(TAG, "onResponse: "+data);
////                back.call(which,data);
//            }
//        });
    }
}
