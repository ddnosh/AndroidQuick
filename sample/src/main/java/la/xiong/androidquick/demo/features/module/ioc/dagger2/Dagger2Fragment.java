package la.xiong.androidquick.demo.features.module.ioc.dagger2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class Dagger2Fragment extends BaseFragment {

    public static final String TAG = "Dagger2Fragment";

    @BindView(R.id.btn_dagger2_1)
    Button btn1;

//    @Inject
//    Dagger2Test1Bean mDagger2Test1Bean;

    @Inject
    Dagger2Test2Bean mDagger2Test2Bean;

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
//        getTest1Component().inject(this);
//        mDagger2TestBean.setName("Jack");
        getTest2Component().inject(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_ioc_dagger2;
    }

    @OnClick({R.id.btn_dagger2_1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_dagger2_1:
//                btn1.setText(mDagger2Test1Bean.getName());
                btn1.setText(mDagger2Test2Bean.getName());
                break;
        }
    }

//    protected Test1Component getTest1Component() {
//        return DaggerTest1Component.builder()
//                .build();
//    }

    protected Test2Component getTest2Component() {
        return DaggerTest2Component.builder()
                .testModule(new TestModule())
                .build();
    }

}
