package la.xiong.androidquick.demo.architecture.design_patterns.proxy;

import android.os.Bundle;
import android.view.View;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;
import la.xiong.androidquick.tool.ToastUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class ProxyFragment extends BaseFragment {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_design_pattern_proxy;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

    }

    @OnClick({R.id.btn_proxy_static, R.id.btn_proxy_dynamic, R.id.btn_proxy_dynamic_factory})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_proxy_static:
                //静态代理
                BenzProxy proxy1 = new BenzProxy();
                proxy1.move();
                break;
            case R.id.btn_proxy_dynamic:
                //动态代理
                ICar car1 = new Benz();
                InvocationHandler handler = new CarHandler(car1);
                ICar proxy2 = (ICar) Proxy.newProxyInstance(ICar.class.getClassLoader(), new Class[]{ICar.class}, handler);
                proxy2.move();
                break;
            case R.id.btn_proxy_dynamic_factory:
                //动态代理+简单工厂
                ProxyFactory factory = new ProxyFactory();//创建工厂
                factory.setBefore(new IBefore() {
                    @Override
                    public void doBefore() {
                        System.out.println("doBefore.");
                    }
                });
                factory.setClient(new Benz());
                factory.setAfter(new IAfter() {
                    @Override
                    public void doAfter() {
                        System.out.println("doAfter.");
                    }
                });
                ICar car2 = (ICar) factory.createProxy();
                car2.move();
                break;
        }
    }

    private interface ICar {
        void move();
    }

    private class Benz implements ICar {

        @Override
        public void move() {
            ToastUtil.showToast("Benz move");
        }
    }

    //代理类
    private class BenzProxy implements ICar {
        private Benz benz;

        public BenzProxy() {
            benz = new Benz();
        }

        @Override
        public void move() {
            //做一些前置工作，比如检查车辆的状况
            //before();
            benz.move();
            //做一些后置工作，比如检查结果
            //after();
        }
    }

    //动态代理类
    public class CarHandler implements InvocationHandler {

        //目标类的引用
        private Object target;

        public CarHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //做一些前置工作，比如检查车辆的状况
            before();

            //调用被代理类的方法
            Object result = method.invoke(target, args);

            //做一些后置工作，比如检查结果
            //after();
            return result;
        }

        private void before() {
            ToastUtil.showToast("before Benz move");
        }
    }

    public class ProxyFactory<T> {

        private T client;//目标对象
        private IBefore before; // 前置增强
        private IAfter after; // 后置增强

        @SuppressWarnings("unchecked")
        public <T> T createProxy() {
            ClassLoader loader = client.getClass().getClassLoader();
            Class[] interfaces = client.getClass().getInterfaces();
            InvocationHandler h = new InvocationHandler() {
                public Object invoke(Object proxy, Method method, Object[] args)
                        throws Throwable {
                    //前置
                    if (before != null) {
                        before.doBefore();
                    }
                    Object result = method.invoke(client, args);//执行目标对象的目标方法
                    if (after != null) {
                        after.doAfter();
                    }
                    return result;
                }
            };
            return (T) Proxy.newProxyInstance(loader, interfaces, h);
        }

        public void setClient(T client) {
            this.client = client;
        }

        public void setBefore(IBefore before) {
            this.before = before;
        }

        public void setAfter(IAfter after) {
            this.after = after;
        }
    }

    public interface IBefore {
        void doBefore();
    }

    public interface IAfter {
        void doAfter();
    }

}
