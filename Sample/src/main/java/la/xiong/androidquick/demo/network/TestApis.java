package la.xiong.androidquick.demo.network;

import java.util.List;

import la.xiong.androidquick.demo.bean.TestBean;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public interface TestApis {

    @GET
    Observable<List<TestBean>> getOctocat(@Url String url);
}
