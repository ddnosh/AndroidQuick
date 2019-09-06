package com.androidwind.androidquick.demo.features.function.ui.constraintlayout;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import butterknife.BindView;


/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"constraint"}, description = "ConstraintLayout实例")
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
