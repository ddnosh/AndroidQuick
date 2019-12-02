package com.androidwind.androidquick.demo.features.module.network.retrofit;

import android.support.annotation.NonNull;

import com.androidwind.androidquick.demo.MyApplication;
import com.androidwind.androidquick.demo.constant.Constants;
import com.androidwind.androidquick.util.FileUtil;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public enum RetrofitManager {
    INSTANCE;

    private final String TAG = "RetrofitManager";

    private HashMap<String, Retrofit> retrofitMap = new HashMap<>();

    private void createRetrofit(@NonNull String baseUrl, boolean isJson) {
        int timeOut = Constants.HTTP_TIME_OUT;
        Cache cache = new Cache(FileUtil.getHttpImageCacheDir(MyApplication.getInstance()),
                Constants.HTTP_MAX_CACHE_SIZE);

        // Log信息拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//这里可以选择拦截级别

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(timeOut, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())//定义转化器,用Gson将服务器返回的Json格式解析成实体
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//关联RxJava
                .client(okHttpClient);

        if(isJson){
            builder.addConverterFactory(GsonConverterFactory.create());
        } else {
            builder.addConverterFactory(SimpleXmlConverterFactory.createNonStrict());
        }

        retrofitMap.put(baseUrl + "-" + isJson, builder.build());
    }

    public Retrofit getRetrofit(@NonNull String baseUrl, boolean isJson) {
        String key = baseUrl + "-" + isJson;
        if (!retrofitMap.containsKey(key)) {
            createRetrofit(baseUrl, isJson);
        }
        return retrofitMap.get(key);
    }

    public Retrofit getRetrofit(@NonNull String baseUrl) {
        return getRetrofit(baseUrl, true);
    }
}
