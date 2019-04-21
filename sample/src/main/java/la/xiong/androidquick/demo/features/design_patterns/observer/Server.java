package la.xiong.androidquick.demo.features.design_patterns.observer;

import java.util.Observable;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class Server extends Observable {

    private int time;

    public Server(int time) {
        this.time = time;
    }

    public void setTime(int time) {
        if (this.time == time) {
            setChanged();//一定要标注, 表明有数据变更, 需要通知订阅者
            notifyObservers(time);
        }
    }
}
