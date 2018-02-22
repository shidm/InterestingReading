package com.sdm.interestingreading.utils;

import java.util.Map;
import java.util.Set;

/**
 * Created by shidongming on 18-2-22.
 */

public class UrlJointUtil {
    public static String getUrl(String baseUrl, Map<String, String> map) {
        String url = null;
        boolean b = true;
        Set<Map.Entry<String, String>> set = map.entrySet();
        for (Map.Entry<String, String> data : set) {
            if (b) {
                b = false;
                url = baseUrl + "?" + data.getKey() + "=" + data.getValue();
            }else
                url = url + "&" + data.getKey() + "=" + data.getValue();
        }
        return url;
    }
}
