package la.xiong.androidquick.demo.features.function.ui.constraintlayout;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import butterknife.BindView;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class ConstraintLayoutFragment extends BaseFragment {

    @BindView(R.id.textView2)
    TextView textView2;

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        new Handler().postDelayed(() -> {
            if (!((Activity) mContext).isFinishing() && !((Activity) mContext).isDestroyed()) {
                textView2.setText("Mike Bolton");
            }
        }, 2000);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_constraintlayout;
    }


}
