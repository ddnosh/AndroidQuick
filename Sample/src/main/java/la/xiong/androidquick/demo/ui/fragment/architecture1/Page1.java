package la.xiong.androidquick.demo.ui.fragment.architecture1;

import la.xiong.androidquick.tool.ToastUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */

public class Page1 extends PageController {
    public Page1(BasePageFragment fragment) {
        super(fragment);
    }

    @Override
    protected void initView() {

    }

    @Override
    public void enter() {
        super.enter();
        ToastUtil.showToast("Page1 entered!");
    }

    @Override
    public void out() {
        super.out();
        ToastUtil.showToast("Page1 outed!");
    }

    @Override
    public void release() {

    }
}
