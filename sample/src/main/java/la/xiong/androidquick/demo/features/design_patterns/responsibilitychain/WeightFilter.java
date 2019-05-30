package la.xiong.androidquick.demo.features.design_patterns.responsibilitychain;

import la.xiong.androidquick.tool.LogUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class WeightFilter extends BaseFilter {
    @Override
    public void doDetailFilter(String name) {
        if (name.contains("5kg")) {
            LogUtil.d("WeightFilter", "the product's weight is 5kg, pass!");
        }
    }
}
