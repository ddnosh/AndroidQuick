package la.xiong.androidquick.tool;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class AppUtil {

    private final static String TAG = "AppUtil";

    /**
     * get App versionCode
     * @param context
     * @return
     */
    public static String getVersionCode(Context context){
        PackageManager packageManager=context.getPackageManager();
        PackageInfo packageInfo;
        String versionCode="";
        try {
            packageInfo=packageManager.getPackageInfo(context.getPackageName(),0);
            versionCode=packageInfo.versionCode+"";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * get App versionName
     * @param context
     * @return
     */
    public static String getVersionName(Context context){
        PackageManager packageManager=context.getPackageManager();
        PackageInfo packageInfo;
        String versionName="";
        try {
            packageInfo=packageManager.getPackageInfo(context.getPackageName(),0);
            versionName=packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public static boolean isContextValid(Context context) {
        if (context == null) {
            LogUtil.d(TAG, "context is null");
            return false;
        }
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                LogUtil.d(TAG, "context is finishing");
                return false;
            }
            if (((Activity) context).isDestroyed()) {
                LogUtil.d(TAG, "context is destroyed");
                return false;
            }
        }
        return true;
    }
}
