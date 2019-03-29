package la.xiong.androidquick.demo.architecture.segment.type2.mvp;

import java.lang.reflect.ParameterizedType;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MVPUtil {
    public static <T> T getT(Object o) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[0])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
