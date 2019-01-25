package la.xiong.androidquick.demo.module.network.retrofit;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public interface GankApis {

    @GET("day/history")
    Observable<GankRes<List<String>>> getHistoryDate();

}
