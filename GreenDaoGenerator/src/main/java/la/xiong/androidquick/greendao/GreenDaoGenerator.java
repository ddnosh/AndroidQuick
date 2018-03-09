package la.xiong.androidquick.greendao;

import java.io.File;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class GreenDaoGenerator {

  public static final int VERSION = 1;
  public static final String GREEN_DAO_CODE_PATH = "Sample/src/main/java";

  public static void main(String[] args) throws Exception {

    Schema schema = new Schema(VERSION, "la.xiong.androidquick.demo.db");

    File f = new File(GREEN_DAO_CODE_PATH);
    if (!f.exists()) {
      f.mkdirs();
    }

    addUser(schema);

    new DaoGenerator().generateAll(schema, f.getAbsolutePath());
  }
  private static void addUser(Schema schema){
    Entity user = schema.addEntity("User");
    user.addIdProperty();
    user.addStringProperty("name");
    user.addIntProperty("age");
  }

}
