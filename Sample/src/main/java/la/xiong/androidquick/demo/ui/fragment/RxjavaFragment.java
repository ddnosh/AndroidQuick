package la.xiong.androidquick.demo.ui.fragment;

import android.view.View;

import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseTFragment;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class RxjavaFragment extends BaseTFragment {

    private String testJsonString;

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_rxjava;
    }

    @OnClick({R.id.btn_tools_rxjava_1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_tools_rxjava_1:

                break;
        }
    }
}
