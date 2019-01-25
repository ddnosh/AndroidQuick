package la.xiong.androidquick.demo.module.network.retrofit.download;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class DownloadProgressInterceptor implements Interceptor {

    private DownloadProgressListener listener;

    public DownloadProgressInterceptor(DownloadProgressListener listener) {
        this.listener = listener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        return originalResponse.newBuilder()
                .body(new DownloadProgressResponseBody(originalResponse.body(), listener))
                .build();
    }
}
