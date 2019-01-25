package la.xiong.androidquick.demo.ui.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;
import la.xiong.androidquick.demo.module.db.GreenDaoFragment;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class RadioButtonFragment extends BaseFragment {

    private Fragment mCatalogue1Fragment;

    @Override
    protected void initViewsAndEvents() {
        mCatalogue1Fragment = new Example1Fragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, mCatalogue1Fragment, Example1Fragment.TAG).commit();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_radiobutton;
    }

    @OnClick({R.id.catalogue1, R.id.catalogue2, R.id.catalogue3, R.id.catalogue4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.catalogue1:
                switchPage(Example1Fragment.TAG, Example1Fragment.class);
                break;
            case R.id.catalogue2:
                switchPage(PermissionFragment.TAG, PermissionFragment.class);
                break;
            case R.id.catalogue3:
                switchPage(JsonFragment.TAG, JsonFragment.class);
                break;
            case R.id.catalogue4:
                switchPage(GreenDaoFragment.TAG, GreenDaoFragment.class);
                break;
        }
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
                getSupportFragmentManager().beginTransaction().replace(R.id.container, instance, tag).hide(mCatalogue1Fragment).commit();
                mCatalogue1Fragment = instance;
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            getSupportFragmentManager().beginTransaction().hide(mCatalogue1Fragment).show(fragment).commit();
            mCatalogue1Fragment = fragment;
        }
    }
}
