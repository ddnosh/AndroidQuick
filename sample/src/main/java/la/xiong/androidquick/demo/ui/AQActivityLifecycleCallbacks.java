package la.xiong.androidquick.demo.ui;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.Stack;

import la.xiong.androidquick.tool.LogUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class AQActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    private static String TAG = "AQActivityLifecycleCallbacks";

    public static Stack<Activity> store = new Stack<>();

    private Activity visibleActivity = null;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        LogUtil.i(TAG, activity.getLocalClassName() + " was Created, " + "activity==null    "
                + (activity == null) + ", activity.isFinishing()    " + (activity.isFinishing()) + ", activity.isDestroyed()    " + activity.isDestroyed());
    }

    @Override
    public void onActivityStarted(Activity activity) {
        LogUtil.i(TAG, activity.getLocalClassName() + " was Started, " + "activity==null    "
                + (activity == null) + ", activity.isFinishing()    " + (activity.isFinishing()) + ", activity.isDestroyed()    " + activity.isDestroyed());
        store.add(activity);
    }

    @Override
    public void onActivityResumed(Activity activity) {
        LogUtil.i(TAG, activity.getLocalClassName() + " was oResumed, " + "activity==null   "
                + (activity == null) + ", activity.isFinishing()    " + (activity.isFinishing()) + ", activity.isDestroyed()    " + activity.isDestroyed());
        visibleActivity = activity;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        LogUtil.i(TAG, activity.getLocalClassName() + " was Pauseed, " + "activity==null  "
                + (activity == null) + ", activity.isFinishing()    " + (activity.isFinishing()) + ", activity.isDestroyed()    " + activity.isDestroyed());
        visibleActivity = null;
    }

    @Override
    public void onActivityStopped(Activity activity) {
        LogUtil.i(TAG, activity.getLocalClassName() + " was Stoped, " + "activity==null   "
                + (activity == null) + ", activity.isFinishing()    " + (activity.isFinishing()) + ", activity.isDestroyed()    " + activity.isDestroyed());
        store.remove(activity);
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        LogUtil.i(TAG, activity.getLocalClassName() + " was SaveInstanceState, " + "activity==null    "
                + (activity == null) + ", activity.isFinishing()    " + (activity.isFinishing()) + ", activity.isDestroyed()    " + activity.isDestroyed());
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        LogUtil.i(TAG, activity.getLocalClassName() + " was Destroyed, " + "activity==null  "
                + (activity == null) + ", activity.isFinishing()    " + (activity.isFinishing()) + ", activity.isDestroyed()    " + activity.isDestroyed());
    }

    public Activity getCurrentVisibleActivity() {
        return visibleActivity;
    }

    public boolean isInForeground() {
        return store.size() > 0;
    }
}
