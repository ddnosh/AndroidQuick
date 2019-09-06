package com.androidwind.androidquick.demo.ui.activity;

import android.os.Bundle;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseActivity;
import com.androidwind.androidquick.ui.base.QuickFragment;
import com.androidwind.androidquick.util.LogUtil;
import com.androidwind.androidquick.util.ReflectUtil;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class FrameActivity extends BaseActivity {

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
    protected void initViewsAndEvents(Bundle savedInstanceState) {
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

}
