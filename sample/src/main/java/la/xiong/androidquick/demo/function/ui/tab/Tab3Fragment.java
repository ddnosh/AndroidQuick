package la.xiong.androidquick.demo.function.ui.tab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import la.xiong.androidquick.demo.MyApplication;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseTFragment;
import la.xiong.androidquick.demo.ui.fragment.Example1Fragment;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class Tab3Fragment extends BaseTFragment {

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @BindView(R.id.viewpagertab)
    SmartTabLayout mSmartTabLayout;

    @Override
    protected void initViewsAndEvents() {
        final List<String> tabs = Arrays.asList(MyApplication.getInstance().getResources()
                .getStringArray(R.array.subtabs));
        mViewPager.setOffscreenPageLimit(tabs.size());
        final PagerAdapter pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return null != tabs ? tabs.size() : 0;
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

            @Override
            public CharSequence getPageTitle(int position) {
                return null != tabs ? tabs.get(position) : null;
            }
        };

        mViewPager.setAdapter(pagerAdapter);

        mSmartTabLayout.setViewPager(mViewPager);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_tab3;
    }
}
