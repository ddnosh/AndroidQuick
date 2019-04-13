package la.xiong.androidquick.demo.features.module.db.greendao;

import java.util.List;

import la.xiong.androidquick.tool.LogUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class RealUserDao {
    private static String TAG = "RealUserDao";
    private static RealUserDao INSTANCE;

    private UserDao userDao;

    public RealUserDao() {
        this.userDao = DBManager.getInstance().getDaoSession().getUserDao();
    }

    public static RealUserDao getInstance() {
        if (INSTANCE == null) {
            synchronized (RealUserDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RealUserDao();
                }
            }
        }
        return INSTANCE;
    }

    public User addUser(User user) {
        try {
            userDao.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogUtil.i(TAG, "new user is inserted, the id is " + user.getId());
        return user;
    }

    public synchronized User queryUser(int id) {
        User user = userDao.queryBuilder().
                where(UserDao.Properties.Id.eq(id)).build().unique();
        return user;
    }

    public synchronized List<User> queryAllUsers() {
        List<User> userList = userDao.queryBuilder().
                orderDesc(UserDao.Properties.Id).list();
        return userList;
    }

    public synchronized void updateUser(User user) {
        if (user == null) {
            return;
        }
        userDao.update(user);
    }

    public synchronized void deleteUser(String name) {
        User user = userDao.queryBuilder().where(UserDao.Properties.Name.eq(name)).build().unique();
        if (user == null) {
            return;
        }
        userDao.delete(user);
    }
}
