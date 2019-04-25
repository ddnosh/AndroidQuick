package la.xiong.androidquick.demo.features.function.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class AnnotationTool<T, V extends Annotation> {

    private static volatile AnnotationTool sInstance = null;
    private AnnotationTool() {

    }
    public static AnnotationTool getInstance () {
        if (sInstance == null) {
            synchronized(AnnotationTool.class){
                if (sInstance == null) {
                    sInstance = new AnnotationTool();
                }
            }
        }
        return sInstance;
    }

    public void check(T t, Class<V> clazz) {
        Method[] method = t.getClass().getDeclaredMethods();
        for ( Method m: method ) {
            if ( m.isAnnotationPresent(clazz)) {
                try {
                    m.setAccessible(true);
                    m.invoke(t, null);
                } catch (Exception e) {
                }
            }
        }
        // 生成测试报告
        System.out.println("Quick Checked");
    }
}
