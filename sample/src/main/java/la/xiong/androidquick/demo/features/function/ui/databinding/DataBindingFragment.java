package la.xiong.androidquick.demo.features.function.ui.databinding;

import android.os.Bundle;

import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.databinding.BaseTFragment;
import la.xiong.androidquick.demo.databinding.FragmentDatabindingBinding;
import la.xiong.androidquick.tool.ToastUtil;

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
