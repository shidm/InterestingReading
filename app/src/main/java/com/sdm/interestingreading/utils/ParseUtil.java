package com.sdm.interestingreading.utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sdm· on 2018/2/22.
 */

public class ParseUtil {

    public static String parseContent(String data){
        try {
            JSONObject jsonObject = new JSONObject(data);
            String info = jsonObject.getString("info");
            String list = jsonObject.getString("list");
            jsonObject = new JSONObject(info);
            String count = jsonObject.getString("count");
            String page = jsonObject.getString("page");
            data = list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
}
