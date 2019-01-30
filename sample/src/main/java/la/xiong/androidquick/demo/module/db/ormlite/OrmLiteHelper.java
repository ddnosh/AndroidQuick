package la.xiong.androidquick.demo.module.db.ormlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class OrmLiteHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME    = "aq-ormlite.db";
    private static final int    DATABASE_VERSION = 1;

    private Dao<User, Integer> mUserDao = null;

    public OrmLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, User.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public <T> Dao<T, Integer> getTDao(Class<T> cls) throws SQLException {
//        return getDao(cls);
//    }
    /* User */

    public Dao<User, Integer> getUserDao() throws SQLException {
        if (mUserDao == null) {
            mUserDao = getDao(User.class);
        }

        return mUserDao;
    }

    @Override
    public void close() {
        mUserDao = null;

        super.close();
    }
}
