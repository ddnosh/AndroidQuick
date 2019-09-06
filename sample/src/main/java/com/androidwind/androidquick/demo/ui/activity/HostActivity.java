package com.androidwind.androidquick.demo.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseActivity;
import com.androidwind.androidquick.demo.ui.fragment.ExampleFragment;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class HostActivity extends BaseActivity {

    private Fragment mContentFragment;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_host;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        FragmentManager fm = getSupportFragmentManager();
        mContentFragment = fm.findFragmentById(R.id.content_frame);

        if(mContentFragment == null )
        {
            mContentFragment = ExampleFragment.newInstance("");
            fm.beginTransaction().add(R.id.content_frame,mContentFragment).commit();
        }
    }
}
