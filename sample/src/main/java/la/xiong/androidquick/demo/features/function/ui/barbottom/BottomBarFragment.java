package la.xiong.androidquick.demo.features.function.ui.barbottom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import butterknife.BindView;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;
import la.xiong.androidquick.demo.features.function.json.JsonFragment;
import la.xiong.androidquick.demo.features.function.permission.PermissionFragment;
import la.xiong.androidquick.demo.features.module.db.greendao.GreenDaoFragment;
import la.xiong.androidquick.demo.ui.fragment.ExampleFragment;
import la.xiong.androidquick.ui.view.BottomBar;
import la.xiong.androidquick.ui.view.BottomBarTab;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"bottombar"}, description = "BottomBar实例")
public class BottomBarFragment extends BaseFragment {
    @BindView(R.id.contentContainer)
    FrameLayout mContentContainer;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;

    private Class[] mFragments = new Class[4];
    private String[] mStrings = new String[4];
    private Fragment mFragment;

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        mStrings[0] = ExampleFragment.TAG;
        mStrings[1] = PermissionFragment.TAG;
        mStrings[2] = JsonFragment.TAG;
        mStrings[3] = GreenDaoFragment.TAG;

        mFragments[0] = ExampleFragment.class;
        mFragments[1] = PermissionFragment.class;
        mFragments[2] = JsonFragment.class;
        mFragments[3] = GreenDaoFragment.class;

        mFragment = ExampleFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.contentContainer, mFragment, ExampleFragment.TAG).commit();

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
