package la.xiong.androidquick.demo.module.ioc;

import android.view.View;

import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseActivity;
import la.xiong.androidquick.demo.module.ioc.butterknife.ButterKnifeFragment;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class IocActivity extends BaseActivity {
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_module_ioc;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @OnClick({R.id.btn_module_butterknife})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_module_butterknife:
                readyGo(ButterKnifeFragment.class);
                break;
        }
    }
}
