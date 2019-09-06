package com.androidwind.androidquick.demo.features.design_patterns.responsibilitychain;

import com.androidwind.androidquick.util.StringUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseFilter {

    protected BaseFilter filter;

    public void setFilter(BaseFilter filter) {
        this.filter = filter;
    }

    public void doFilter(String name) {
        if (!StringUtil.isEmpty(name)) {
            doDetailFilter(name);
            if (filter != null) {
                filter.doFilter(name);
            }
        }
    }

    protected abstract void doDetailFilter(String name);
}
