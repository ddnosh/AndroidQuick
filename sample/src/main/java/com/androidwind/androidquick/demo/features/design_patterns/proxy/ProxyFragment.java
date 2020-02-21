package com.androidwind.androidquick.demo.features.design_patterns.proxy;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.androidwind.androidquick.demo.R;
import com.androidwind.androidquick.demo.base.BaseActivity;
import com.androidwind.androidquick.demo.base.BaseFragment;
import com.androidwind.androidquick.demo.constant.Constants;
import com.androidwind.androidquick.demo.features.function.permission.EasyPermissions;
import com.androidwind.androidquick.ui.dialog.dialogactivity.ADialog;
import com.androidwind.androidquick.ui.dialog.dialogactivity.BaseDialog;
import com.androidwind.androidquick.util.LogUtil;
import com.androidwind.androidquick.util.ToastUtil;
import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import butterknife.OnClick;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"proxy", "代理", "AOP", "静态", "动态", "retrofit"}, description = "静态代理 + 动态代理 + AOP")
public class ProxyFragment extends BaseFragment {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_design_pattern_proxy;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

    }

    @OnClick({R.id.btn_proxy_static, R.id.btn_proxy_dynamic, R.id.btn_proxy_dynamic_factory, R.id.btn_proxy_aop, R.id.btn_proxy_aop_retrofit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_proxy_static:
                //静态代理
                ICar benz = new Benz();
                ICar proxy1 = new BenzProxy(benz);
                proxy1.move();
                break;
            case R.id.btn_proxy_dynamic:
                //动态代理
                ICar bmw = new Benz();
                InvocationHandler handler = new CarHandler(bmw);
                ICar proxy2 = (ICar) Proxy.newProxyInstance(ICar.class.getClassLoader(), new Class[]{ICar.class}, handler);
                proxy2.move();
                //另一种写法
                ICar proxy3 = (ICar) Proxy.newProxyInstance(ICar.class.getClassLoader(), new Class[]{ICar.class}, (proxy, method, args) -> {
                    //调用被代理类的方法
                    return method.invoke(bmw, args);
                });
                proxy3.move();
                break;
            case R.id.btn_proxy_dynamic_factory:
                //动态代理+简单工厂
                ProxyFactory factory = new ProxyFactory();
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
            case R.id.btn_proxy_aop:
                ProxyAOPFactory aopFactory = new ProxyAOPFactory();
                aopFactory.setClient(new Benz());
                aopFactory.setBefore(new IBefore() {
                    @Override
                    public void doBefore() {
                        permissionsCheck();
                    }
                });
                ICar car3 = (ICar) aopFactory.createProxy();
                car3.move();
                break;
            case R.id.btn_proxy_aop_retrofit:
                IRequestAPI api = create(IRequestAPI.class);
                api.getHistory("123");
                api.getNew();
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
        private ICar benz;

        public BenzProxy(ICar benz) {
            this.benz = benz;
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
                    if("getName".equals(method.getName())){
                        //可根据name值过滤方法
                    }
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

    //AOP
    public class ProxyAOPFactory<T> {

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
                    if("getName".equals(method.getName())){
                        //可根据name值过滤方法
                    }
                    //前置
                    if (before != null) {
                        before.doBefore();
                    }
                    Object result = method.invoke(client, args);//执行目标对象的目标方法
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
    }


    private void permissionsCheck() {
        String[] perms = {Manifest.permission.CALL_PHONE//电话
        };
        performCodeWithPermission(1, Constants.RC_PERMISSION_PERMISSION_FRAGMENT, perms, new BaseActivity.PermissionCallback() {
            @Override
            public void hasPermission(List<String> allPerms) {
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:000")));
            }

            @Override
            public void noPermission(List<String> deniedPerms, List<String> grantedPerms, Boolean hasPermanentlyDenied) {
                if (hasPermanentlyDenied) {
                    EasyPermissions.goSettingsPermissions(getActivity(), 2, Constants.RC_PERMISSION_PERMISSION_FRAGMENT, Constants.RC_PERMISSION_BASE);
                }
            }

            @Override
            public void showDialog(int dialogType, final EasyPermissions.DialogCallback callback) {
                switch (dialogType){
                    case 1:
                        new ADialog(mContext)
                                .setConvertListener((BaseDialog.ViewConvertListener) (holder, dialog) -> {
                                    holder.setText(R.id.dialog_title, getString(R.string.app_name));
                                    holder.setText(R.id.dialog_info, getString(R.string.dialog_phone_permission));
                                    holder.setText(R.id.dialog_confirm, "OK");
                                    holder.setOnClickListener(R.id.dialog_confirm, v -> {
                                        dialog.dismiss();
                                        callback.onGranted();
                                    });
                                }).show();
                        break;
                    case 2:
                        new ADialog(mContext)
                                .setConvertListener((BaseDialog.ViewConvertListener) (holder, dialog) -> {
                                    holder.setText(R.id.dialog_title, getString(R.string.app_name));
                                    holder.setText(R.id.dialog_info, getString(R.string.dialog_phone_permission));
                                    holder.setText(R.id.dialog_confirm, "Go to setting");
                                    holder.setOnClickListener(R.id.dialog_confirm, v -> {
                                        dialog.dismiss();
                                        callback.onGranted();
                                    });
                                }).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public <T> T create(final Class<T> service) {
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service},
                new InvocationHandler() {

                    @Override
                    public Object invoke(Object proxy, Method method, Object... args)
                            throws Throwable {
                        GET getType = method.getAnnotation(GET.class);
                        LogUtil.i(TAG, "IRequestAPI---getType->" + getType.value());
                        Annotation[][] parameterAnnotationsArray = method.getParameterAnnotations();
                        for (int i = 0; i < parameterAnnotationsArray.length; i++) {
                            Annotation[] annotations = parameterAnnotationsArray[i];
                            if (annotations != null) {
                                Query queryParam = (Query) annotations[0];
                                LogUtil.i(TAG, "queryParam---queryParam->" + queryParam.value() + "==" + args[i]);
                            }
                        }
                        //··这里开始处理网络请求
                        // HttpGet、HttpPost
                        //··网络请求处理完毕
                        final Object object;

                        if (String.class.equals(method.getReturnType())) {
                            String result = "test";
                            object = result;
                            return object;
                        } else if (Observable.class.equals(method.getReturnType())) {
                            return Observable.just("123");
                        }
                        return null;
                    }
                });
    }
}
