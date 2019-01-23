package la.xiong.androidquick.tool;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class GsonHelper {

    private static final String TAG = "GsonHelper";

    private static Gson gson = null;
    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    /**
     * 返回指定key的值
     *
     * @param json
     * @param key
     * @param type 数据类型0表示String类，1表示int类型
     * @return
     */
    public static Object getField(String json, String key, int type) {
        if (StringUtil.isEmpty(json) || StringUtil.isEmpty(key))
            return null;
        try {
            JSONObject object = new JSONObject(json);
            if (object.has(key)) {
                if (type == 0) {
                    return new String(object.getString(key));
                } else {
                    return new Integer(object.getInt(key));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T fromJson(String JSONString, TypeToken<T> typeToken) {
        if (TextUtils.isEmpty(JSONString))
            return null;

        T t = null;
        try {
            t = gson.fromJson(JSONString, typeToken.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return t;
    }

    public static <T> T fromJson(String JSONString, Class<T> classOfT) {
        if (TextUtils.isEmpty(JSONString))
            return null;

        T t = null;
        try {
            t = gson.fromJson(JSONString, classOfT);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return t;
    }

    public static String toJsonString(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }
}
