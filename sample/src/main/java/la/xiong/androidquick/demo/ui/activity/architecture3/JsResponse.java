package la.xiong.androidquick.demo.ui.activity.architecture3;

import android.support.annotation.Keep;
import android.support.annotation.Nullable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@Keep

public class JsResponse<T> {

    private int code;

    private String msg;

    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Nullable
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @JSONField(serialize = false, deserialize = false)
    public boolean isSuccess() {
        return code == 0;
    }

    public static <T> JsResponse<T> success(T data) {
        JsResponse<T> response = new JsResponse<>();
        response.data = data;
        return response;
    }

    public static <T> JsResponse<T> error(int code, String msg) {
        JsResponse<T> response = new JsResponse<>();
        response.code = code;
        response.msg = msg;
        return response;
    }

    @Override
    public String toString() {
        return JsonToObject.toJsonString(this);
    }
}
