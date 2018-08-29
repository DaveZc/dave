package com.chenZ.dave.common;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * Created by chen.z on 2018/8/3.
 */
public class Test {
    public static void main(String[] args) {
        String str = "{deviceId:\"767530B2-86FB-4A20-9FBF-BA148F869D80\";mpType:\"ios\";mpModal:\"iPhone 7\";osVersion:\"11.4.1\";appVersion:\"2.4.2\"}";
        str = str.replace("{", "");
        str = str.replace("}", "");
        str = str.replace("\"", "");
        String[] infos = str.split(";");
        Map<String, String> map = Maps.newHashMap();
        for (String s : infos) {
            if (StringUtils.isBlank(s.trim()) || !s.contains(":")) {
                continue;
            }
            String[] data = s.split(":");
            map.put(data[0], data[1]);
        }
        System.out.println(map);
    }
}
