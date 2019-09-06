package com.androidwind.androidquick.demo.features.function.ui.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.androidwind.androidquick.demo.MyApplication;
import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.androidquick.demo.ui.fragment.ExampleFragment;
import com.androidwind.androidquick.util.ToastUtil;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;


/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"tab"}, description = "SmartTabLayout实例")
public class Tab3Fragment extends BaseFragment {

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @BindView(R.id.viewpagertab)
    SmartTabLayout mSmartTabLayout;

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
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

    //懒加载：只有在ViewPager+FragmentPagerAdapter+Fragment结合使用时才会用到
    @Override
    protected void onFirstUserVisible() {
        ToastUtil.showToast("这是懒加载成功的提示");
    }
}
