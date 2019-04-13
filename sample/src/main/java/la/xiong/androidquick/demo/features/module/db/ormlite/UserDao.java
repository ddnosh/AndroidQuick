package la.xiong.androidquick.demo.features.module.db.ormlite;

import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class UserDao extends BaseOrmLiteDao<User> {

    private static UserDao sINSTANCE;

    public static UserDao getInstance() {
        if (sINSTANCE == null) {
            synchronized (UserDao.class) {
                if (sINSTANCE == null) {
                    sINSTANCE = new UserDao();
                }
            }
        }
        return sINSTANCE;
    }

    public void createUser(String name) {
        User user = new User();
        user.setName(name);
        try {
            dao.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int userId) {
        try {
            QueryBuilder<User, Integer> qb = dao.queryBuilder();
            qb.where().eq(User.FIELD_NAME_ID, userId);
            return qb.queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUser() {
        try {
            QueryBuilder<User, Integer> qb = dao.queryBuilder();
            return qb.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(User user) {
        try {
            dao.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            dao.deleteBuilder().delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
