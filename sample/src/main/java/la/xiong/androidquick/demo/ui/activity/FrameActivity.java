package la.xiong.androidquick.demo.ui.activity;

import android.os.Bundle;

import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseTActivity;
import la.xiong.androidquick.tool.LogUtil;
import la.xiong.androidquick.tool.ReflectUtil;
import la.xiong.androidquick.ui.base.QuickFragment;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class FrameActivity extends BaseTActivity {

    protected static String TAG = "FrameActivity";
    private Bundle bundle;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_frame;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        bundle = extras;
    }

    @Override
    protected void initViewsAndEvents() {
        String className = bundle.getString("fragmentName");
        LogUtil.i(TAG, "the fragment class name is->" + className);
        if (className != null) {
            Object object = ReflectUtil.getObject(className);
            if (object instanceof QuickFragment) {
                QuickFragment fragment = (QuickFragment) object;
                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commitAllowingStateLoss();
                }
            } else {
                LogUtil.e(TAG, " the fragment class is not exist!!!");
            }
        }
    }

    @Override
    protected void initInjector() {

    }
}
