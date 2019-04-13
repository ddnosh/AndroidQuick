package la.xiong.androidquick.demo.features.architecture.segment.type3;

import com.alibaba.fastjson.JSONObject;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public interface JsInterface {

    /**
     * js调用native的接口
     *
     * @param name
     * @param request
     */
    void invoke(String name, JSONObject request);

}
