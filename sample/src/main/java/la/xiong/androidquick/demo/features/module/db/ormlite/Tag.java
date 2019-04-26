package la.xiong.androidquick.demo.features.module.db.ormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@DatabaseTable(tableName = Tag.TABLE_NAME_TAG)
public class Tag implements Serializable {

    public static final String TABLE_NAME_TAG = "tag";
    
    public static final String FIELD_NAME_LOCATION   = "location";
    public static final String FIELD_NAME_DETAIL   = "detail";

    @DatabaseField(columnName = FIELD_NAME_LOCATION, id = true, uniqueIndex = true)
    private String mLocation;
    @DatabaseField(columnName = FIELD_NAME_DETAIL)
    private String mDetail;

    public Tag() {
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public String getDetail() {
        return mDetail;
    }

    public void setDetail(String detail) {
        mDetail = detail;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "mLocation='" + mLocation + '\'' +
                ", mDetail='" + mDetail + '\'' +
                '}';
    }
}
