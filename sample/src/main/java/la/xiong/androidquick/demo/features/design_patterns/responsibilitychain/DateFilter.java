package la.xiong.androidquick.demo.features.design_patterns.responsibilitychain;

import la.xiong.androidquick.tool.LogUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class DateFilter extends BaseFilter {
    @Override
    public void doDetailFilter(String name) {
        if (name.contains("2019")) {
            LogUtil.d("WeightFilter", "the product's date is 2019, pass!");
        }
    }
}
