package com.androidwind.androidquick.demo.features.architecture.segment.type3;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.androidwind.androidquick.util.LogUtil;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class JsonToObject {
    private static final String TAG = "JsonToObject";

    @Nullable
    public static final <T> T toObject(String json, Class<T> cls) {
        if ( null == json ){
            return null;
        }

        try{
            return JSON.parseObject(json, cls);
        }
        catch (Exception e){
            LogUtil.e(TAG, "toObject: " + e.getMessage());
        }
        return null;
    }

    @Nullable
    public static final <T> List<T> toArray(String json, Class<T> cls) {
        if ( null == json ){
            return null;
        }

        try{
            return JSON.parseArray(json, cls);
        }
        catch (Exception e){
            LogUtil.e(TAG, "toArray: " + e.getMessage());
        }
        return null;
    }

    public static JSONObject toObject(String json) {
        if ( null == json ){
            return null;
        }

        try{
            return JSON.parseObject(json);
        }
        catch (Exception e){
            LogUtil.e(TAG, "toObject: " + e.getMessage());
        }
        return null;
    }

    public static String toJsonString(Object object) {
        return JSON.toJSONString(object);
    }

    public static <T> T toObject(String json, TypeReference<T> typeReference) {
        return JSON.parseObject(json, typeReference.getType());
    }
}
