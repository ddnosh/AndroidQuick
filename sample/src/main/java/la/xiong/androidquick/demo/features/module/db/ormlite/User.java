package la.xiong.androidquick.demo.features.module.db.ormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */

@DatabaseTable(tableName = User.TABLE_NAME_USERS)
public class User implements Serializable {

    public static final String TABLE_NAME_USERS = "users";

    public static final String FIELD_NAME_ID     = "id";
    public static final String FIELD_NAME_NAME   = "name";

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int mId;

    @DatabaseField(columnName = FIELD_NAME_NAME)
    private String mName;

    public User() {
        // Don't forget the empty constructor, needed by ORMLite.
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }
}