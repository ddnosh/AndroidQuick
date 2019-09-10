package com.androidwind.annotation.core;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;

import com.androidwind.annotation.annotation.TagInfo;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class SearchEngine {

    private static SearchEngine sInstance;

    public static SearchEngine getsInstance() {
        if (sInstance == null) {
            synchronized (SearchEngine.class) {
                if (sInstance == null) {
                    sInstance = new SearchEngine();
                }
            }
        }
        return sInstance;
    }

    public void init() {
        Class<?> aClass = null;
        try {
            aClass = Class.forName("com.androidwind.annotation.TagService");
            ILoad tagService = (ILoad) aClass.newInstance();
            tagService.load(SearchHouse.tags);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public void jumpTo(Context context, TagInfo tagInfo) {
        if (tagInfo.getType() == TagInfo.Type.ACTIVITY) {
            Intent intent = null;
            try {
                intent = new Intent(context, Class.forName(tagInfo.getLocation()));
                ActivityCompat.startActivity(context, intent, null);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (tagInfo.getType() == TagInfo.Type.FRAGMENT) {
            Intent intent = null;
            try {
                intent = new Intent(context, Class.forName("com.androidwind.androidquick.demo.ui.activity.FrameActivity"));
                intent.putExtra("fragmentName", tagInfo.getLocation());
                ActivityCompat.startActivity(context, intent, null);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
