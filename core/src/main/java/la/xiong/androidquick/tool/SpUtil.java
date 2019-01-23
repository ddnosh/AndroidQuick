package la.xiong.androidquick.tool;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class SpUtil {
    public static final String PREF_FILE_NAME = "android_pref_file";

    public static Context mContext;

    public static void init(Context context) {
        mContext = context.getApplicationContext();
    }

    /**
     * 清除所有数据
     */
    public static void clear() {
        clear(PREF_FILE_NAME);
    }

    public static void clear(String file) {
        SharedPreferences sp = mContext.getSharedPreferences(file, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().apply();
    }

    /**
     * 保存数据
     */
    public static void setParam(String key, Object object) {
        setParam(PREF_FILE_NAME, key, object);
    }

    public static void setParam(String file, String key, Object object) {

        String type = object.getClass().getSimpleName();

        SharedPreferences sp = mContext.getSharedPreferences(file,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if ("String".equals(type)) {
            editor.putString(key, (String) object);
        } else if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) object);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) object);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) object);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }

        editor.apply();
    }

    /**
     * 查询数据
     */
    public static Object getParam(String key, Object defaultObject) {
        return getParam(PREF_FILE_NAME, key, defaultObject);
    }

    public static Object getParam(String file, String key, Object defaultObject) {
        if (defaultObject == null) {
            return null;
        }
        try {
            String type = defaultObject.getClass().getSimpleName();

            SharedPreferences sp = mContext.getSharedPreferences(file,
                    Context.MODE_PRIVATE);

            if ("String".equals(type)) {
                return sp.getString(key, (String) defaultObject);
            } else if ("Integer".equals(type)) {
                return sp.getInt(key, (Integer) defaultObject);
            } else if ("Boolean".equals(type)) {
                return sp.getBoolean(key, (Boolean) defaultObject);
            } else if ("Float".equals(type)) {
                return sp.getFloat(key, (Float) defaultObject);
            } else if ("Long".equals(type)) {
                return sp.getLong(key, (Long) defaultObject);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 移除某个key值已经对应的值
     */
    public static void remove(Context context, String key) {
        remove(context, PREF_FILE_NAME, key);
    }

    public static void remove(Context context, String file, String key) {
        SharedPreferences sp = context.getSharedPreferences(file,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }
}
