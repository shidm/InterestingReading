package com.sdm.interestingreading.utils;

import java.util.Map;
import java.util.Set;

/**
 * Created by shidongming on 18-2-22.
 */

public class UrlJointUtil {
    public static String getUrl(String baseUrl, Map<String, String> map) {
        String url = baseUrl
                + "?showapi_appid=" + BaseData.SHOWAPI_APPID
                + "&showapi_sign=" + BaseData.SHOWAPI_SIGN;
        boolean b = true;
        Set<Map.Entry<String, String>> set = map.entrySet();
        for (Map.Entry<String, String> data : set) {
                url = url + "&" + data.getKey() + "=" + data.getValue();
        }
        return url;
    }
}
