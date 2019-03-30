package la.xiong.androidquick.module.network.retrofit.exeception;

import android.util.Log;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.text.ParseException;

import retrofit2.HttpException;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class ExeceptionEngine {

    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ApiException handleException(Throwable throwable) {
        Log.e("e", throwable.getMessage() + throwable);
        ApiException ex;
        if (throwable instanceof HttpException) {             //HTTP错误
            HttpException httpException = (HttpException) throwable;
            ex = new ApiException(throwable, ErrorType.HTTP_ERROR);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                    ex.message = "当前请求需要用户验证";
                    break;
                case FORBIDDEN:
                    ex.message = "服务器已经理解请求，但是拒绝执行它";
                    break;
                case NOT_FOUND:
                    ex.message = "服务器异常，请稍后再试";
                    break;
                case REQUEST_TIMEOUT:
                    ex.message = "请求超时";
                    break;
                case GATEWAY_TIMEOUT:
                    ex.message = "服务器没有从远端服务器得到及时的响应";
                    break;
                case INTERNAL_SERVER_ERROR:
                    ex.message = "服务器内部错误";
                    break;
                case BAD_GATEWAY:
                    ex.message = "服务器接收到远端服务器的错误响应";
                    break;
                case SERVICE_UNAVAILABLE:
                    ex.message = "服务器维护或者过载，服务器当前无法处理请求";
                    break;
                default:
                    ex.message = "网络错误";  //其它均视为网络错误
                    break;
            }
        } else if (throwable instanceof ServerException) {    //服务器返回的错误
            ServerException resultException = (ServerException) throwable;
            ex = new ApiException(resultException, resultException.code);
            ex.message = resultException.message;
            return ex;
        } else if (throwable instanceof SocketTimeoutException) {
            ex = new ApiException(throwable, ErrorType.NETWORK_ERROR);
            ex.message = "服务器响应超时";
        } else if (throwable instanceof ConnectException) {
            ex = new ApiException(throwable, ErrorType.NETWORK_ERROR);
            ex.message = "网络连接异常，请检查网络";
        } else if (throwable instanceof UnknownHostException || throwable instanceof UnknownServiceException || throwable instanceof IOException) {
            ex = new ApiException(throwable, ErrorType.NO_NETWORK);
            ex.message = "设备当前未联网，请检查网络设置";
        } else if (throwable instanceof RuntimeException) {
            ex = new ApiException(throwable, ErrorType.RUN_TIME);
            ex.message = "很抱歉,程序运行出现了错误";
        } else if (throwable instanceof JsonParseException
                || throwable instanceof JSONException
                || throwable instanceof ParseException) {
            ex = new ApiException(throwable, ErrorType.PARSE_ERROR);
            ex.message = "解析错误";
        } else {
            ex = new ApiException(throwable, ErrorType.UNKNOWN);
            ex.message = "未知错误";
        }
        return ex;
    }

}
