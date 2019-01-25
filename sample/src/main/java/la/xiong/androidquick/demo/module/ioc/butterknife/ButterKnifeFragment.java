package la.xiong.androidquick.demo.module.ioc.butterknife;

import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseTFragment;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class ButterKnifeFragment extends BaseTFragment {

    public static final String TAG = "GreenDaoFragment";

    @BindView(R.id.btn_bf1)
    Button btn1;

    @Override
    protected void initViewsAndEvents() {
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_ioc_butterknife;
    }

    @OnClick({R.id.btn_bf1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_bf1:
                btn1.setText("Clicked");
                break;
        }
    }

}
