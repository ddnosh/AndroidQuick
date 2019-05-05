package la.xiong.androidquick.demo.features.function.ui.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;
import com.flyco.tablayout.SlidingTabLayout;

import butterknife.BindView;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;
import la.xiong.androidquick.demo.ui.fragment.ExampleFragment;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"tab"}, description = "SlidingTabLayout实例")
public class TabFTLFragment extends BaseFragment {

    @BindView(R.id.vp_tab_ftl)
    ViewPager mViewPager;

    @BindView(R.id.ftl_main_tab)
    SlidingTabLayout mSlidingTabLayout;

    private String[] mTitles = {"Tab1", "Tab2"};

    private MyPagerAdapter mAdapter;

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

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
                    fragment = new ExampleFragment();
                    break;
                case 1:
                    fragment = new ExampleFragment();
                    break;
                default:
                    break;
            }

            return fragment;
        }
    }
}
