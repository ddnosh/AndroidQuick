package la.xiong.androidquick.demo.ui.fragment;

import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFFragment;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class Example4Fragment extends BaseFFragment {

    public static Example4Fragment newInstance() {
        return new Example4Fragment();
    }

    @Override
    protected void initViewsAndEvents() {

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
        // ContentFragment是ShopFragment的栈顶子Fragment,可以在此处理返回按键事件
        return super.onBackPressedSupport();
    }
}
