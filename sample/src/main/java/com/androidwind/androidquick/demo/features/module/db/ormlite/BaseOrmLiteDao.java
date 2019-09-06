package com.androidwind.androidquick.demo.features.module.db.ormlite;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;

import com.androidwind.androidquick.demo.MyApplication;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class BaseOrmLiteDao<T> {

    protected static OrmLiteHelper ormLiteHelper = OpenHelperManager.getHelper(MyApplication.getInstance(), OrmLiteHelper.class);

    protected Dao<T, Integer> dao;

    {
        try {
            dao = ormLiteHelper.getDao(getTClass());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Class<T> getTClass()
    {
        Class<T> tClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }
}
