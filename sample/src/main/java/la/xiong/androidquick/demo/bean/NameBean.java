package la.xiong.androidquick.demo.bean;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class NameBean {

    private String age;
    private int id;
    private String name;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NameBean{" +
                "age='" + age + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
