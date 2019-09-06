package com.androidwind.androidquick.demo.features.design_patterns.proxy;

import java.util.List;

import io.reactivex.Observable;
import com.androidwind.androidquick.demo.features.module.network.retrofit.GankRes;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public interface IRequestAPI {

    @GET("day/history")
    Observable<GankRes<List<String>>> getHistory(@Query("param") String param);

    @GET("day/new")
    String getNew();
}
