package la.xiong.androidquick.demo.architecture.design_patterns.single;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class SingletonDemo {

    private static volatile SingletonDemo sInstance = null;
    private SingletonDemo() {

    }
    public static SingletonDemo getInstance () {
        if (sInstance == null) {
            synchronized(SingletonDemo.class){
                if (sInstance == null) {
                    sInstance = new SingletonDemo();
                }
            }
        }
        return sInstance;
    }

    public void printSomething() {
        System.out.println("this is a singleton");
    }
}