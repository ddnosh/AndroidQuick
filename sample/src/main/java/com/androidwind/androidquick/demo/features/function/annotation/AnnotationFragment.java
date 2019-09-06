package com.androidwind.androidquick.demo.features.function.annotation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.androidquick.demo.features.function.annotation.aspect.CheckNetwork;
import com.androidwind.androidquick.demo.features.function.annotation.manager.InjectManager;
import com.androidwind.androidquick.util.LogUtil;
import com.androidwind.androidquick.util.ToastUtil;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import butterknife.OnClick;


/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"annotation", "注解", "反射"}, description = "自定义注解(annotation)实例")
public class AnnotationFragment extends BaseFragment {

    @ByString(R.string.annotation_reflection)
    private String text;
    @ByColor(R.color.colorAccent)
    private int color;
    @ByView(R.id.btn_annotation_with_reflection)
    private Button btn;

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        // AnnotationTool.getInstance().check(this, QuickCheck.class);
        // login();
        InjectManager.inject(this);//注入
        btn.setText(text);
        btn.setTextColor(color);
    }

    // @QuickCheck
    // private void login() {
    //     LogUtil.i("AnnotationFragment", "login()");
    // }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_function_annotation;
    }

    @OnClick({R.id.btn_annotation_with_javapoet, R.id.btn_annotation_with_aspectj})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_annotation_with_javapoet:
                ToastUtil.showToast("参考aop-annotation && aop-compiler && aop-core工程的实现");
                break;
            case R.id.btn_annotation_with_aspectj:
                checkNetwork();
                break;
            default:
                break;
        }
    }

    @CheckNetwork()
    private void checkNetwork() {
        LogUtil.i("AnnotationFragment", "检测完毕");
    }
}
