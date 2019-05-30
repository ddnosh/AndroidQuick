package la.xiong.androidquick.demo.features.design_patterns.responsibilitychain;

import la.xiong.androidquick.tool.LogUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class ShapeFilter extends BaseFilter {
    @Override
    public void doDetailFilter(String name) {
        if (name.contains("circle")) {
            LogUtil.d("ShapeFilter", "the product's shape is circle, pass!");
        }
    }
}
