package la.xiong.androidquick.demo.features.module.db.ormlite;

import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class TagDao extends BaseOrmLiteDao<Tag> {
    private static TagDao sINSTANCE;

    public static TagDao getInstance() {
        if (sINSTANCE == null) {
            synchronized (UserDao.class) {
                if (sINSTANCE == null) {
                    sINSTANCE = new TagDao();
                }
            }
        }
        return sINSTANCE;
    }

    public void createOrUpdate(String location, String detail) {

        Tag tag = new Tag();
        tag.setLocation(location);
        tag.setDetail(detail);
        try {
            dao.createOrUpdate(tag);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Tag> getAllTag() {
        try {
            QueryBuilder<Tag, Integer> qb = dao.queryBuilder();
            return qb.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
