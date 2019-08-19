package la.xiong.androidquick.demo.features.design_patterns.template;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class Car {
    void startUp() {
        System.out.println("启动！");
    }

    abstract void move();//强制要求实现

    void stop() {
        System.out.println("熄火！");
    }

    public final void operation(){//定义成final, 防止被重写
        //第一步：启动
        startUp();
        //第二步：驾驶
        move();
        //第三步：停止
        stop();
    }
}
