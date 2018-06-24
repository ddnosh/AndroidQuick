package la.xiong.androidquick.demo.ui.activity;

import android.view.View;

import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseActivity;
import la.xiong.androidquick.demo.ui.fragment.architecture1.Architecture1Fragment;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class ArchitectureActivity extends BaseActivity {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_architecture;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @OnClick({R.id.btn_architecture_one_layout_with_multiple_views})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_architecture_one_layout_with_multiple_views:
                readyGo(Architecture1Fragment.class);
                break;
        }
    }

    @Override
    protected boolean isLoadDefaultTitleBar() {
        return true;
    }
}
