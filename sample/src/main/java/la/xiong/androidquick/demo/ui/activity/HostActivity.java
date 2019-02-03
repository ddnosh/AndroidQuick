package la.xiong.androidquick.demo.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseActivity;
import la.xiong.androidquick.demo.ui.fragment.Example1Fragment;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class HostActivity extends BaseActivity {

    private Fragment mContentFragment  ;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_host;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        FragmentManager fm = getSupportFragmentManager();
        mContentFragment = (Fragment) fm.findFragmentById(R.id.content_frame);

        if(mContentFragment == null )
        {
            mContentFragment = Example1Fragment.createIntent("");
            fm.beginTransaction().add(R.id.content_frame,mContentFragment).commit();
        }
    }
}
