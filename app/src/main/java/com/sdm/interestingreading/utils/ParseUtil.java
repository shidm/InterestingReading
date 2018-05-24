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

    public static String[] parseComment(String data){
        String[] strings = new String[2];
        try {
            JSONObject jsonObject = new JSONObject(data);
            String total = jsonObject.getString("total");
            String theData = jsonObject.getString("data");
            strings[0] = total;
            strings[1] = theData;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return strings;
    }
}
