package la.xiong.androidquick.demo.features.function.ui.fragment;

import android.os.Bundle;

import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.fragmentation.BaseFFragment;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"fragment"}, description = "Example3Fragment实例")
public class Example3Fragment extends BaseFFragment {

    public static Example3Fragment newInstance() {
        return new Example3Fragment();
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        if (findChildFragment(Example4Fragment.class) == null) {
            // false:  不加入回退栈;  false: 不显示动画
            loadRootFragment(R.id.fl_content_container, Example4Fragment.newInstance(), false, false);
        }
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_example3;
    }
}
