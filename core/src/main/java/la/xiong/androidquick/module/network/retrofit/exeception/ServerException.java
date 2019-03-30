package la.xiong.androidquick.module.network.retrofit.exeception;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class ServerException extends RuntimeException{
    // 异常处理，为速度，不必要设置getter和setter
    public int code;
    public String message;

    public ServerException( int code,String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
