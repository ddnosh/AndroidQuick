package la.xiong.androidquick.demo.module.ioc.dagger2;

import javax.inject.Inject;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class Dagger2Test1Bean {
    @Inject
    public Dagger2Test1Bean() {
    }

    private String name;

    private int age;

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
        return "Dagger2Test1Bean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
