package la.xiong.androidquick.demo.features.module.network.retrofit;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public interface GankApis {

    @GET("day/history")
    Observable<GankRes<List<String>>> getHistoryDate();

}
