package la.xiong.androidquick.demo.module.bus;

import android.view.View;

import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseActivity;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class BusActivity extends BaseActivity {
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_module_bus;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @OnClick({R.id.btn_module_eventbus})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_module_eventbus:
                readyGo(EventBusFragment.class);
                break;
        }
    }
}
