package la.xiong.androidquick.demo.module.mvp.fragment;

import android.os.Bundle;
import android.widget.TextView;

import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseTFragment;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MvpFragment extends BaseTFragment<MvpPresenter> implements MvpContract.View{

    private TextView mContent;

    @Override
    protected void initInjector() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_module_mvp_fragment;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        mContent = getActivity().findViewById(R.id.tv_fragment_mvp);
        mContent.setText("this is MvpFragment");
        mPresenter.initData("hello");
    }

    @Override
    public void refreshView(final String result) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mContent.setText(result);
            }
        });
    }

}
