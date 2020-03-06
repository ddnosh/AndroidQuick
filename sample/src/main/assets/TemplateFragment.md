## TemplateFragment
<pre>
    @Override
    public void clickItem(int position) {
        switch (position) {
            case 0:
                Car bmw = new BMW();
                bmw.move();
                Car benz = new Benz();
                benz.move();
                break;
        }
    }

    public class BMW extends Car {
    @Override
    void move() {
        System.out.println("BMW烧汽油动起来了");
    }
}
    public class Benz extends Car {
    @Override
    void move() {
        System.out.println("Benz靠电动起来了");
    }
}
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

</pre>