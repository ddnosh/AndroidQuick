package com.androidwind.androidquick.demo.tool;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.androidwind.androidquick.demo.bean.MenuBean;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MenuUtil {

    private List<MenuBean> position;

    public List<MenuBean> getPositions(Context context, String fileName) {
        if (position == null) {
            initPositions(context, fileName);
        }
        return position;
    }

    private void initPositions(Context context, String fileName) {
        String industryString = readAssetsTXT(context, fileName);
        String[] strings = industryString.split(";");
        position = new ArrayList<MenuBean>();
        for (int i = 0; i < strings.length; i++) {
            String[] items = strings[i].trim().split(",");
            MenuBean tmp = new MenuBean();
            tmp.currentId = Long.parseLong(items[0].trim());
            tmp.name = items[1];
            tmp.upperId = Long.parseLong(items[2].trim());
            position.add(tmp);
        }
    }

    public String readAssetsTXT(Context context, String fName) {
        try {
            AssetManager assetManager = context.getAssets();
            InputStream is = assetManager.open(fName);
            byte[] bytes = new byte[1024];
            int length;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((length = is.read(bytes)) != -1) {
                baos.write(bytes, 0, length);
            }
            return new String(baos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
