package la.xiong.androidquick.demo.features.design_patterns.responsibilitychain;

import android.os.Bundle;
import android.view.View;

import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"responsibility", "责任链"}, description = "责任链模式实例")
public class ResponsibilityChainFragment extends BaseFragment {

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_design_pattern_responsibilitychain;
    }

    @OnClick(R.id.btn_rc)
    public void onClick(View view) {
        if (view.getId() == R.id.btn_rc) {
            BaseFilter shape = new ShapeFilter();
            BaseFilter weight = new WeightFilter();
            BaseFilter date = new DateFilter();
            shape.setFilter(weight);
            weight.setFilter(date);
            shape.doFilter("circle5kg2019");
        }
    }
}
