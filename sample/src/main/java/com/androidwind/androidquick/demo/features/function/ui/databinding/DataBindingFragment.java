package com.androidwind.androidquick.demo.features.function.ui.databinding;

import android.os.Bundle;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.databinding.BaseTFragment;
import com.androidwind.androidquick.demo.databinding.FragmentDatabindingBinding;
import com.androidwind.androidquick.util.ToastUtil;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;



/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"databinding"}, description = "DataBinding实例")
public class DataBindingFragment extends BaseTFragment<FragmentDatabindingBinding> {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_databinding;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        getBinding().setAct(this);
        getBinding().tvDatabindingEmpty.setText("this is a databinding view");
    }

    public void onConfirmClick() {
        ToastUtil.showToast("clicked");
    }

}
