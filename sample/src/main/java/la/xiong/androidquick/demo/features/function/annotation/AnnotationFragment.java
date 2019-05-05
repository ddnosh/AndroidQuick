package la.xiong.androidquick.demo.features.function.annotation;

import android.os.Bundle;

import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;
import la.xiong.androidquick.tool.LogUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"annotation", "注解", "反射"}, description = "自定义注解(annotation)实例")
public class AnnotationFragment extends BaseFragment {
    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        AnnotationTool.getInstance().check(this, QuickCheck.class);
        login();
    }

    @QuickCheck
    private void login() {
        LogUtil.i("AnnotationFragment", "login()");
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_function_ui_refresh_smartrefreshlayout;
    }
}
