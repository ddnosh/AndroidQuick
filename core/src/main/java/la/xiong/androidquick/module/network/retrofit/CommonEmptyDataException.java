package la.xiong.androidquick.module.network.retrofit;

import la.xiong.androidquick.constant.Constant;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class CommonEmptyDataException extends RuntimeException {
    //错误码
    private int errorCode = Constant.ERROR_CODE_EMPTYDATA;
    //错误消息
    private String errorMsg;


    public CommonEmptyDataException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
