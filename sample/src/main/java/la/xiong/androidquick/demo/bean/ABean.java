package la.xiong.androidquick.demo.bean;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class ABean {

    private String name;
    private int age;

    public ABean(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ABean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
