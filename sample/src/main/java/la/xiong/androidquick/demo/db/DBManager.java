package la.xiong.androidquick.demo.db;

import android.content.Context;

import java.util.List;

import la.xiong.androidquick.tool.LogUtil;

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
                DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, context.getPackageName() + ".db", null);
                daoMaster = new DaoMaster(helper.getWritableDatabase());
            }
            daoSession = daoMaster.newSession();
        }
    }

    public User addUser(User user) {
        try {
            UserDao userDao = daoSession.getUserDao();
            userDao.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogUtil.i(TAG, "new user is inserted, the id is " + user.getId());
        return user;
    }

    public synchronized User queryUser(int id) {
        User user = daoSession.getUserDao().queryBuilder().
                where(UserDao.Properties.Id.eq(id)).build().unique();
        return user;
    }

    public synchronized List<User> queryAllUsers() {
        List<User> userList = daoSession.getUserDao().queryBuilder().
                orderDesc(UserDao.Properties.Id).list();
        return userList;
    }

    public synchronized void updateUser(User user) {
        if (user == null) {
            return;
        }
        daoSession.getUserDao().update(user);
    }

    public synchronized void deleteUser(String name) {
        User user = daoSession.getUserDao().queryBuilder().where(UserDao.Properties.Name.eq(name)).build().unique();
        if (user == null) {
            return;
        }
        daoSession.getUserDao().delete(user);
    }
}
