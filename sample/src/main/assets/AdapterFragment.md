## AdapterFragment
<pre>
    @Override
    public void clickItem(int position) {
        switch (position) {
            case 0:
                IChineseCharger iChineseCharger = new ChineseCharger();
                Adapter adapter = new Adapter(iChineseCharger);
                AmericanDevice device = new AmericanDevice(adapter);
                device.work();
                break;
        }
    }

    public class Adapter implements IAmericanCharger {
    private IChineseCharger mIChineseCharger;
    public Adapter(IChineseCharger iChineseCharger) {
        mIChineseCharger = iChineseCharger;
    }

    @Override
    public void charge4American() {
        mIChineseCharger.charge4Chinese();
    }
}

    public class AmericanCharger implements IAmericanCharger {
    @Override
    public void charge4American() {
        LogUtil.i("AmericanCharger", "do American charge");
    }
}

    public class AmericanDevice {

    private IAmericanCharger mIAmericanCharger;

    public AmericanDevice(IAmericanCharger IAmericanCharger) {
        mIAmericanCharger = IAmericanCharger;
    }

    public void work() {
        mIAmericanCharger.charge4American();
        LogUtil.i("AmericanDevice", "美规电器开始运行！");
    }
}

    public class ChineseCharger implements IChineseCharger {
    @Override
    public void charge4Chinese() {
        LogUtil.i("AmericanCharger", "do Chinese charge");
    }
}

    public interface IAmericanCharger {

    void charge4American();
}

    public interface IChineseCharger {

    void charge4Chinese();
}
</pre>