package com.androidwind.androidquick.demo.features.design_patterns.factory;

import com.androidwind.androidquick.demo.features.CodeAndRunFragment;
import com.androidwind.androidquick.demo.view.bottom.BottomDialogFragment;
import com.androidwind.androidquick.util.ToastUtil;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"factory", "工厂"}, description = "简单工厂 + 工厂方法 + 抽象工厂")
public class FactoryFragment extends CodeAndRunFragment {

    @Override
    public String getMarkDownUrl() {
        return "FactoryFragment";
    }

    @Override
    public String[] getItems() {
        return new String[]{"简单工厂", "工厂方法实现1", "工厂方法实现2", "工厂方法实现3", "抽象工厂"};
    }

    @Override
    public void clickItem(int position) {
        switch (position) {
            case 0:
                //简单工厂
                ICar car1 = SimpleFactory.getCar(0);
                car1.move();
                break;
            case 1:
                //工厂方法实现1
                IFactory factory = new BenzFactory();
                ICar car2 = factory.getCar();
                car2.move();
                break;
            case 2:
                //工厂方法实现2
                ICar bmw = CarFactory.createCar(BMW.class);
                if (bmw != null) {
                    bmw.move();
                }
                break;
            case 3:
                //工厂方法实现3
                try {
                    ICar ACar = EnumCarFactory.valueOf("Benz").create();
                    ACar.move();
                } catch (Exception e) {
                    System.out.println("无效参数,无法初始化");
                }
                break;
            case 4:
                //抽象工厂
                IAbsFactory absFactory = new ZhangSan();
                ICar car = absFactory.getCar();
                car.move();
                IClothes clothes = absFactory.getClothes();
                clothes.wear();
                break;
        }
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
    private class BenzFactory implements IFactory {

        public ICar getCar() {
            return new Benz();
        }
    }

    private class BMWFactory implements IFactory {

        public ICar getCar() {
            return new BMW();
        }
    }

    enum EnumCarFactory {
        Benz {
            @Override
            public ICar create() {
                return new Benz();
            }
        },
        BMW {
            @Override
            public ICar create() {
                return new BMW();
            }
        };

        public abstract ICar create();
    }

    //抽象工厂
    private class ZhangSan implements IAbsFactory {

        @Override
        public ICar getCar() {
            return new Benz();
        }

        @Override
        public IClothes getClothes() {
            return new Gucci();
        }
    }

    private class LiSi implements IAbsFactory {

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
