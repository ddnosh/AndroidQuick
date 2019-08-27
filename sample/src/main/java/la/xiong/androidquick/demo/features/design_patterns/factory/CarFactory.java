package la.xiong.androidquick.demo.features.design_patterns.factory;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class CarFactory {
    public static ICar createCar(Class<? extends ICar> c) {
        try {
            return (ICar) c.newInstance();
        } catch (Exception e) {
            System.out.println("初始化失败");
        }
        return null;
    }
}
