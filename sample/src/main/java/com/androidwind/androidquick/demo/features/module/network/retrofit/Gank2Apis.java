package com.androidwind.androidquick.demo.features.module.network.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public interface Gank2Apis {

    @GET("day/history")
    Call<GankRes<List<String>>> getHistoryDate();

}
