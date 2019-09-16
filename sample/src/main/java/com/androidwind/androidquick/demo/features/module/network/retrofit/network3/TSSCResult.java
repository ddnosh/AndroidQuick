package com.androidwind.androidquick.demo.features.module.network.retrofit.network3;

import java.io.Serializable;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class TSSCResult implements Serializable {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
