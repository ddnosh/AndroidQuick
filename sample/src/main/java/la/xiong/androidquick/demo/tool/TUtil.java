package la.xiong.androidquick.demo.tool;

import android.support.annotation.NonNull;

import java.lang.reflect.ParameterizedType;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class TUtil {
    public static <T> T getInstance(Object object, int i) {
        if(object!=null){
            try {
                return ((Class<T>) ((ParameterizedType) (object.getClass()
                        .getGenericSuperclass())).getActualTypeArguments()[i])
                        .newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassCastException e) {
                e.printStackTrace();
            }

        }
        return null;

    }

    public static @NonNull
    <T> T checkNotNull(final T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }
}
