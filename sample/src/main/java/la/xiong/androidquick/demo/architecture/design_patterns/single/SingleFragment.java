package la.xiong.androidquick.demo.architecture.design_patterns.single;

import android.os.Bundle;
import android.view.View;

import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class SingleFragment extends BaseFragment {
    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_design_pattern_single;
    }

    @OnClick({R.id.btn_single, R.id.btn_static})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_single:
                SingletonDemo.getInstance().printSomething();
                break;
            case R.id.btn_static:
                StaticDemo.printSomething();
                break;
        }
    }
}
