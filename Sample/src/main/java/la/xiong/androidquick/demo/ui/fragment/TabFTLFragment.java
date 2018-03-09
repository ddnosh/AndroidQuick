package la.xiong.androidquick.demo.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;

import butterknife.BindView;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class TabFTLFragment extends BaseFragment {

    @BindView(R.id.vp_tab_ftl)
    ViewPager mViewPager;

    @BindView(R.id.ftl_main_tab)
    SlidingTabLayout mSlidingTabLayout;

    private String[] mTitles = {"Tab1", "Tab2"};

    private MyPagerAdapter mAdapter;

    @Override
    protected void initViewsAndEvents() {

        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mSlidingTabLayout.setViewPager(mViewPager, mTitles);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_tab_ftl;
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new Example1Fragment();
                    break;
                case 1:
                    fragment = new Example1Fragment();
                    break;
                default:
                    break;
            }

            return fragment;
        }
    }
}
