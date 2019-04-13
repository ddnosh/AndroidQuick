package la.xiong.androidquick.demo.features.function.ui.tab;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;
import la.xiong.androidquick.demo.features.module.network.retrofit.network1.Network1Fragment;
import la.xiong.androidquick.demo.features.module.network.retrofit.network2.Network2Fragment;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class TabLayoutFragment extends BaseFragment {

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @BindView(R.id.tablayout)
    TabLayout mTabLayout;

    private FmPagerAdapter pagerAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private String[] titles = new String[] {"new", "hot"};

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        for (int i = 0; i < titles.length; i++) {
            switch (i) {
                case 0:
                    fragments.add(new Network1Fragment());
                    break;
                case 1:
                    fragments.add(new Network2Fragment());
                    break;
            }
            mTabLayout.addTab(mTabLayout.newTab());
        }

        mTabLayout.setupWithViewPager(mViewPager, false);
        pagerAdapter = new FmPagerAdapter(fragments, getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(titles.length);
        mViewPager.setAdapter(pagerAdapter);

        for (int i = 0; i < titles.length; i++) {
            mTabLayout.getTabAt(i).setText(titles[i]);
        }
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_tab_tablayout;
    }

    class FmPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList;

        public FmPagerAdapter(List<Fragment> fragmentList, FragmentManager fm) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public int getCount() {
            return fragmentList != null && !fragmentList.isEmpty() ? fragmentList.size() : 0;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }
    }
}
