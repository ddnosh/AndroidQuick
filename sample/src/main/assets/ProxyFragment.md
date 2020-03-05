##ProxyFragment
<pre>
private BottomDialogFragment.OnBottomItemClick listener = position -> {
        switch (position) {
            case 0:
                //静态代理
                ICar benz = new Benz();
                ICar proxy1 = new BenzProxy(benz);
                proxy1.move();
                break;
            case 1:
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
            case 2:
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
            case 3:
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
            case 4:
                IRequestAPI api = create(IRequestAPI.class);
                api.getHistory("123");
                api.getNew();
                break;
        }
    };

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
                    if ("getName".equals(method.getName())) {
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
                    if ("getName".equals(method.getName())) {
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
                switch (dialogType) {
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
</pre>