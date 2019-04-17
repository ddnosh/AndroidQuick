package la.xiong.androidquick.demo.features.other.rxlifecycle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.mvp_dagger2.BaseTFragment;
import la.xiong.androidquick.tool.ToastUtil;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class RxJavaLifecycleFragment extends BaseTFragment<RxJavaLifecyclePresenter> implements RxJavaLifecycleContract.View{

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

    @OnClick({R.id.btn_rxlifecycle, R.id.btn_compositedisposable})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_rxlifecycle:
                mPresenter.initDataRxLifecycle();
                break;
            case R.id.btn_compositedisposable:
                mPresenter.initDataCompositeDisposable();
                break;
        }
    }

    @Override
    public void updateView(String result) {
        ToastUtil.showToast("Done!");
    }
}
