package la.xiong.androidquick.demo.function.ui.fragment;

import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFFragment;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class Example3Fragment extends BaseFFragment {

    public static Example3Fragment newInstance() {
        return new Example3Fragment();
    }

    @Override
    protected void initViewsAndEvents() {
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
