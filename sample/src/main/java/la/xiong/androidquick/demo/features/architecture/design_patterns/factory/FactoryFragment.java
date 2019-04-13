package la.xiong.androidquick.demo.features.architecture.design_patterns.factory;

import android.os.Bundle;

import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;
import la.xiong.androidquick.tool.ToastUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class FactoryFragment extends BaseFragment {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_design_pattern_factory;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        //简单工厂
        ICar car1 = SimpleFactory.getCar(0);
        car1.move();
        //工厂方法
        IFactory factory = new BenzFactory();
        ICar car2 = factory.getCar();
        car2.move();
        //抽象工厂
        IAbsFactory absFactory = new Zhangsan();
        ICar car = absFactory.getCar();
        car.move();
        IClothes clothes = absFactory.getClothes();
        clothes.wear();
    }

    private interface ICar {
        void move();
    }

    private static class Benz implements ICar {

        @Override
        public void move() {
            ToastUtil.showToast("Benz moved!");
        }
    }

    private static class BMW implements ICar {

        @Override
        public void move() {
            ToastUtil.showToast("BMW moved!");
        }
    }

    private interface IFactory {
        ICar getCar();
    }

    private interface IClothes {
        void wear();
    }

    private static class Gucci implements IClothes {

        @Override
        public void wear() {
            ToastUtil.showToast("wear Gucci");
        }
    }

    private static class Prada implements IClothes {

        @Override
        public void wear() {
            ToastUtil.showToast("wear Prada");
        }
    }

    public interface IAbsFactory {
        ICar getCar();
        IClothes getClothes();
    }

    //简单工厂
    private static class SimpleFactory {
        public static ICar getCar(int carType) {
            switch (carType) {
                case 0:
                    return new Benz();
                case 1:
                    return new BMW();
            }
            return null;
        }
    }

    //工厂方法
    private class BenzFactory implements IFactory{

        public ICar getCar() {
            return new Benz();
        }
    }

    private class BMWFactory implements IFactory{

        public ICar getCar() {
            return new BMW();
        }
    }

    //抽象工厂
    private class Zhangsan implements IAbsFactory {

        @Override
        public ICar getCar() {
            return new Benz();
        }

        @Override
        public IClothes getClothes() {
            return new Gucci();
        }
    }

    private class Lisi implements IAbsFactory {

        @Override
        public ICar getCar() {
            return new BMW();
        }

        @Override
        public IClothes getClothes() {
            return new Prada();
        }
    }

}
