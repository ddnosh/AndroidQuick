package la.xiong.androidquick.demo.other.rxlifecycle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseTFragment;
import la.xiong.androidquick.tool.ToastUtil;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class RxLifecycleFragment extends BaseTFragment<RxLifecyclePresenter> implements RxLifecycleContract.View{

    @BindView(R.id.btn_rxlifecycle)
    Button btnRxLifecycle;

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

    }

    @Override
    protected void initInjector() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_other_rxlifecycle;
    }

    @OnClick({R.id.btn_rxlifecycle})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_rxlifecycle:
                mPresenter.initData();
                break;
        }
    }

    @Override
    public void updateView(String result) {
        ToastUtil.showToast("Done!");
    }
}
