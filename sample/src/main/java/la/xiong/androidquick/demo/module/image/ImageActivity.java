package la.xiong.androidquick.demo.module.image;

import android.view.View;

import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseActivity;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class ImageActivity extends BaseActivity {
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_module_image;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @OnClick({R.id.btn_module_image})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_module_image:
                readyGo(GlideFragment.class);
                break;
        }
    }
}
