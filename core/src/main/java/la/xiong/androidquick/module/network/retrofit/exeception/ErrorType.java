package la.xiong.androidquick.module.network.retrofit.exeception;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public interface ErrorType {
    /**
     * 正常
     */
    int SUCCESS = 0;
    /**
     * 未知错误
     */
    int UNKNOWN = 1000;
    /**
     * 解析错误
     */
    int PARSE_ERROR = 1001;
    /**
     * 没有网络
     */
    int NO_NETWORK = 1002;
    /**
     * 网络错误 请求超时等
     */
    int NETWORK_ERROR = 1003;
    /**
     * 协议出错 404 500 等
     */
    int HTTP_ERROR = 1004;

    int RUN_TIME= 1005;
}
