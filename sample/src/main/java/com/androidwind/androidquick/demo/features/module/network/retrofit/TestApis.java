package com.androidwind.androidquick.demo.features.module.network.retrofit;

import com.androidwind.androidquick.demo.bean.NameBean;
import com.androidwind.androidquick.demo.bean.TestBean;
import com.androidwind.androidquick.demo.features.module.network.retrofit.network3.TSSCResult;
import com.androidwind.androidquick.demo.features.module.network.retrofit.network3.TSSCRes;
import com.androidwind.androidquick.demo.features.module.network.retrofit.network3.XHYResult;
import com.androidwind.androidquick.demo.features.module.network.retrofit.network3.XHYRes;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public interface TestApis {

    @GET
    Observable<List<TestBean>> getOctocat(@Url String url);

    @GET("https://api.github.com/repos/octocat/Hello-World/contributors")
    Observable<List<TestBean>> getOctocat();

    @GET("http://www.xiong.la/hello")
    Observable<List<NameBean>> getTestData();

    @GET("https://api.bintray.com/packages/ddnosh/maven/androidquick/images/download.svg")
    Observable<String> getSdkVersion();

    @GET("http://api.avatardata.cn/TangShiSongCi/Search?key=bda1beab1af4413f98c603e01c7d6b84&keyWord=秋兴")
    Observable<TSSCRes<TSSCResult>> getTangShiSongCi();

    @GET("http://api.avatardata.cn/XieHouYu/Search?key=3f4f79db909345a58c5361911d7355e0&keyWord=%E7%9E%8E%E5%AD%90%E7%82%B9%E7%81%AF")
    Observable<XHYRes<XHYResult>> getXHY();
}
