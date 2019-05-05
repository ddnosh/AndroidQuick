package la.xiong.androidquick.demo.features.function.ui.fragment;

import android.os.Bundle;

import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.fragmentation.BaseFFragment;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"fragment"}, description = "Example4Fragment实例")
public class Example4Fragment extends BaseFFragment {

    public static Example4Fragment newInstance() {
        return new Example4Fragment();
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_example4;
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultNoAnimator();
    }

    @Override
    public boolean onBackPressedSupport() {
        return super.onBackPressedSupport();
    }
}
