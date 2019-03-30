package la.xiong.androidquick.module.network.http;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public interface HttpCallback {
    void onSuccess(String response);

    void onFail(String response);
}
