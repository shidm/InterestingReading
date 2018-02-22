package com.sdm.interestingreading.utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sdm· on 2018/2/22.
 */

public class ParseUtil {

    public static String parse(String data){
        try {
            JSONObject jsonObject = new JSONObject(data);
            data = jsonObject.getString("showapi_res_body");
            jsonObject = new JSONObject(data);
            data = jsonObject.getString("pagebean");
            jsonObject = new JSONObject(data);
            data = jsonObject.getString("contentlist");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
}
