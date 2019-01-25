package la.xiong.androidquick.demo.ui.fragment;

import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

import butterknife.BindView;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;
import la.xiong.androidquick.demo.module.db.GreenDaoFragment;
import la.xiong.androidquick.ui.view.BottomBar;
import la.xiong.androidquick.ui.view.BottomBarTab;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class BottomBarFragment extends BaseFragment {
    @BindView(R.id.contentContainer)
    FrameLayout mContentContainer;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;

    private Class[] mFragments = new Class[4];
    private String[] mStrings = new String[4];
    private Fragment mFragment;

    @Override
    protected void initViewsAndEvents() {
        mStrings[0] = Example1Fragment.TAG;
        mStrings[1] = PermissionFragment.TAG;
        mStrings[2] = JsonFragment.TAG;
        mStrings[3] = GreenDaoFragment.TAG;

        mFragments[0] = Example1Fragment.class;
        mFragments[1] = PermissionFragment.class;
        mFragments[2] = JsonFragment.class;
        mFragments[3] = GreenDaoFragment.class;

        mFragment = Example1Fragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.contentContainer, mFragment, Example1Fragment.TAG).commit();

        mBottomBar.addItem(new BottomBarTab(getActivity(), R.mipmap.ic_launcher, "富强"))
                .addItem(new BottomBarTab(getActivity(), R.mipmap.ic_launcher, "民主"))
                .addItem(new BottomBarTab(getActivity(), R.mipmap.ic_launcher, "文明"))
                .addItem(new BottomBarTab(getActivity(), R.mipmap.ic_launcher, "和谐"));
        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                switchPage(mStrings[position], mFragments[position]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_bottombar;
    }

    /**
     * @param tag 标记名
     * @param cls 需要创建的Fragment的类名
     */
    private void switchPage(String tag, Class cls) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (fragment == null) {
            try {
                // 通过反射创建出该类对象
                Fragment instance = (Fragment) cls.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer, instance, tag).hide(mFragment).commit();
                mFragment = instance;
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            getSupportFragmentManager().beginTransaction().hide(mFragment).show(fragment).commit();
            mFragment = fragment;
        }
    }
}
