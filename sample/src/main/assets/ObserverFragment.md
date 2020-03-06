## ObserverFragment
<pre>
    @Override
    public void clickItem(int position) {
        switch (position) {
            case 0:
                server = new Server(2019);
                Client client1 = new Client("张三");
                Client client2 = new Client("李四");
                server.addObserver(client1);
                server.addObserver(client2);
                server.setTime(2018);
                server.setTime(2019);
                break;
            case 1:
                MyObservable.notify(2019);
                break;
        }
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        myObserver = new MyObservable.MyObserver() {
            @Override
            public void update(int time) {
                ToastUtil.showToast("here is myObserver!");
            }
        };
        MyObservable.addObserver(myObserver);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (server != null) {
            server.deleteObservers();
        }
        if (myObserver != null) {
            MyObservable.removeObserver(myObserver);
        }
    }


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


public class Client implements Observer {
    private String name;
    public Client(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Server) {
            ToastUtil.showToast(name + "say: time is up!" + arg);
        }
    }
}



public class MyObservable {

    private static Vector<MyObserver> observers = new Vector<>();

    public static void addObserver(MyObserver myObserver) {
        observers.add(myObserver);
    }

    public static void removeObserver(MyObserver myObserver) {
        observers.remove(myObserver);
    }

    public interface MyObserver {
        void update(int time);
    }

    public static void notify(int time) {
        for (MyObserver observer : observers) {
            observer.update(time);
        }
    }
}
</pre>