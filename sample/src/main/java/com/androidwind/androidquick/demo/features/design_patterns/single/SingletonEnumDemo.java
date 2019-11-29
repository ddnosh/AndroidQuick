package com.androidwind.androidquick.demo.features.design_patterns.single;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public enum SingletonEnumDemo {

    INSTANCE;

    public void printSomething() {
        System.out.println("this is a singleton");
    }
}