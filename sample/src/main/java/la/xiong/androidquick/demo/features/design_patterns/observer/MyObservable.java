package la.xiong.androidquick.demo.features.design_patterns.observer;

import java.util.Vector;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MyObservable {

    private static Vector<MyObserver> observers = new Vector<>();

    public static void addObserver(MyObserver myObserver) {
        observers.add(myObserver);
    }

    public static void removeObserver(MyObserver myObserver) {
        observers.remove(myObserver);
    }

    public interface MyObserver {
        void update(int time);
    }

    public static void notify(int time) {
        for (MyObserver observer : observers) {
            observer.update(time);
        }
    }
}
