package com.androidwind.androidquick.demo.features.module.network.retrofit.download;

import com.androidwind.androidquick.demo.features.module.network.retrofit.DownloadApis;
import com.androidwind.androidquick.demo.features.module.network.retrofit.exception.CustomizeException;
import com.androidwind.androidquick.util.FileUtil;
import com.androidwind.androidquick.util.LogUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class DownloadManager {
    private static final String TAG = "DownloadManager";
    private static final int DEFAULT_TIMEOUT = 15;
    public Retrofit retrofit;


    public DownloadManager(String url, DownloadProgressListener listener) {

        DownloadProgressInterceptor interceptor = new DownloadProgressInterceptor(listener);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();


        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public void downloadAPK(@NonNull String url, final File file, Observer observer) {
        LogUtil.d(TAG, "downloadAPK step1(download url): " + url);
        retrofit.create(DownloadApis.class)
                .downloadFile(url)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map(new Function<ResponseBody, InputStream>() {
                    @Override
                    public InputStream apply(ResponseBody responseBody) {
                        return responseBody.byteStream();
                    }
                })
                .observeOn(Schedulers.computation())
                .doOnNext(new Consumer<InputStream>() {
                    @Override
                    public void accept(InputStream inputStream) {
                        try {
                            FileUtil.writeFile(inputStream, file);
                        } catch (IOException e) {
                            e.printStackTrace();
                            throw new CustomizeException(e.getMessage(), e);
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
