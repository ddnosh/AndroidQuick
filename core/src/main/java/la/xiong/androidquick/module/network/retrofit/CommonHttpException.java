package la.xiong.androidquick.module.network.retrofit;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class CommonHttpException extends RuntimeException {
    //错误码
    private int errorCode;
    //错误消息
    private String errorMsg;

    public CommonHttpException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
