package la.xiong.androidquick.demo.features.module.db.greendao;

import android.content.Context;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class DBManager {

    private static String TAG = "DBManager";
    private static DBManager INSTANCE;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    public static DBManager getInstance() {
        if (INSTANCE == null) {
            synchronized (DBManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DBManager();
                }
            }
        }
        return INSTANCE;
    }

    public void init(Context context) {
        if (daoSession == null) {
            if (daoMaster == null) {
                DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, "aq-green.db", null);
                daoMaster = new DaoMaster(helper.getWritableDatabase());
            }
            daoSession = daoMaster.newSession();
        }
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
