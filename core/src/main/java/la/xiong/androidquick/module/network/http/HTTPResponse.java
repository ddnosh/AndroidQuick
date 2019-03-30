package la.xiong.androidquick.module.network.http;

public class HTTPResponse {
    private int code;
    private String responseMessage;
    private String errorMessage;

    public HTTPResponse(int code, String responseMessage, String errorMessage) {
        this.code = code;
        this.responseMessage = responseMessage;
        this.errorMessage = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
