package com.androidwind.androidquick.demo.features.function.permission;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AfterPermissionGranted {

    int value();

}