## SingleFragment
<pre>
    @Override
    public void clickItem(int position) {
        switch (position) {
            case 0:
                SingletonDemo.getInstance().printSomething();
                SingletonEnumDemo.INSTANCE.printSomething();
                break;
            case 1:
                StaticDemo.printSomething();
                break;
        }
    }

    public class SingletonDemo {

        private static volatile SingletonDemo sInstance = null;
        private SingletonDemo() {

        }
        public static SingletonDemo getInstance () {
            if (sInstance == null) {
                synchronized(SingletonDemo.class){
                    if (sInstance == null) {
                        sInstance = new SingletonDemo();
                    }
                }
            }
            return sInstance;
        }

        public void printSomething() {
            System.out.println("this is a singleton");
        }
    }

    public enum SingletonEnumDemo {

        INSTANCE;

        public void printSomething() {
            System.out.println("this is a singleton");
        }
    }

    public class StaticDemo {

        public static void printSomething() {
            System.out.println("this is a static");
        }
    }
</pre>