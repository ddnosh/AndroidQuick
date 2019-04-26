package la.xiong.androidquick.demo.features.module.db.ormlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import la.xiong.androidquick.tool.LogUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class OrmLiteHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "aq-ormlite.db";
    private static final int LATEST_DATABASE_VERSION = 2;

    public OrmLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, LATEST_DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        LogUtil.i("OrmLiteHelper", "[新安装]新数据库版本号:" + LATEST_DATABASE_VERSION);
        update(db, LATEST_DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        int old = db.getVersion();
        LogUtil.i("OrmLiteHelper", "[老用户升级]老数据库版本号:" + old);
        update(db, old);
    }

    private void update(SQLiteDatabase db, int from) {
        try {
            switch (from) {
                case 1:
                    TableUtils.createTableIfNotExists(connectionSource, User.class);
                    break;
                default:
                    createTable();
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() {
        try {
            TableUtils.createTableIfNotExists(connectionSource, User.class);
            TableUtils.createTableIfNotExists(connectionSource, Tag.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        super.close();
    }
}
