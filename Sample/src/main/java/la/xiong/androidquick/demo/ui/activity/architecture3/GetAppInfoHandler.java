package la.xiong.androidquick.demo.ui.activity.architecture3;

import com.alibaba.fastjson.JSONObject;


/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */

public class GetAppInfoHandler extends BaseJsApiHandler<JSONObject> {

    @Override
    public String name() {
        return "getAppInfo";
    }

    @Override
    protected void handelInBackground(JsRequest<JSONObject> jsRequest) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("os", "android");

        setResponse(jsRequest, JsResponse.success(jsonObject));
    }
}
