package la.xiong.androidquick.demo.function.ui.fragment;

import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFFragment;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class Example2Fragment extends BaseFFragment {

    public static Example2Fragment newInstance() {
        return new Example2Fragment();
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_example2;
    }
}
