package la.xiong.androidquick.demo.module.network.retrofit.download;

import android.support.annotation.NonNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import la.xiong.androidquick.demo.module.network.retrofit.DownloadApis;
import la.xiong.androidquick.demo.module.network.retrofit.exception.CustomizeException;
import la.xiong.androidquick.tool.FileUtil;
import la.xiong.androidquick.tool.LogUtil;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author  ddnosh
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
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public Subscription downloadAPK(@NonNull String url, final File file, Subscriber subscriber) {
        LogUtil.d(TAG, "downloadAPK step1(download url): " + url);

        return retrofit.create(DownloadApis.class)
                .downloadFile(url)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map(new Func1<ResponseBody, InputStream>() {
                    @Override
                    public InputStream call(ResponseBody responseBody) {
                        return responseBody.byteStream();
                    }
                })
                .observeOn(Schedulers.computation())
                .doOnNext(new Action1<InputStream>() {
                    @Override
                    public void call(InputStream inputStream) {
                        try {
                            FileUtil.writeFile(inputStream, file);
                        } catch (IOException e) {
                            e.printStackTrace();
                            throw new CustomizeException(e.getMessage(), e);
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


}
