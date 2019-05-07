package la.xiong.androidquick.demo.features.function.annotation.manager;


import android.view.View;

import com.blankj.utilcode.util.ReflectUtils;

import java.lang.reflect.Field;

import la.xiong.androidquick.demo.features.function.annotation.ByColor;
import la.xiong.androidquick.demo.features.function.annotation.ByContentView;
import la.xiong.androidquick.demo.features.function.annotation.ByString;
import la.xiong.androidquick.demo.features.function.annotation.ByView;

public class InjectManagerService {

    public static void injectContentView(ViewManager viewManager, Object object) {
        injectContentViewById(viewManager, object);
    }

    private static void injectContentViewById(ViewManager viewManager, Object object) {
        Class<?> clazz = object.getClass();
        ByContentView annotation = clazz.getAnnotation(ByContentView.class);
        if (annotation != null) {
            int layoutId = annotation.value();
            viewManager.setContentView(layoutId);
        }
    }

    /**
     * 注入变量
     */
    public static void injectFieldById(ViewManager viewManager, Object object) {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if (fields != null) {
            for (Field field : fields) {
                injectViewById(viewManager, object, field);
                injectStringById(viewManager, object, field);
                injectColorById(viewManager, object, field);
            }

        }
    }

    private static void injectViewById(ViewManager viewManager, Object object, Field field) {
        ByView viewById = field.getAnnotation(ByView.class);
        if (viewById != null) {
            int viewId = viewById.value();
            View view = viewManager.findViewById(viewId);
            try {
                ReflectUtils.reflect(object).field(field.getName(), view);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void injectStringById(ViewManager viewManager, Object object, Field field) {
        ByString stringById = field.getAnnotation(ByString.class);
        if (stringById != null) {
            int stringId = stringById.value();
            String string = viewManager.getString(stringId);
            try {
                ReflectUtils.reflect(object).field(field.getName(), string);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void injectColorById(ViewManager viewManager, Object object, Field field) {
        ByColor colorById = field.getAnnotation(ByColor.class);
        if (colorById != null) {
            int colorId = colorById.value();
            int color = viewManager.getColor(colorId);
            try {
                ReflectUtils.reflect(object).field(field.getName(), color);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // /**
    //  * 注入事件
    //  *
    //  * @param viewManager
    //  * @param object
    //  */
    // public static void injectEvent(ViewManager viewManager, Object object) {
    //     injectOnClick(viewManager, object);
    //     injectOnLongClick(viewManager, object);
    // }
    //
    // /**
    //  * 注入点击事件
    //  *
    //  * @param viewManager
    //  * @param object
    //  */
    // private static void injectOnClick(ViewManager viewManager, final Object object) {
    //     Class<?> clazz = object.getClass();
    //     Method[] methods = clazz.getDeclaredMethods();
    //     if (methods != null) {
    //         for (final Method method : methods) {
    //             OnClick onClick = method.getAnnotation(OnClick.class);
    //             if (onClick != null) {
    //                 int[] viewIds = onClick.value();
    //                 for (int viewId : viewIds) {
    //                     View view = viewManager.findViewById(viewId);
    //                     final boolean isCheckNet = method.getAnnotation(CheckNet.class) != null;
    //                     if (view != null) {
    //                         view.setOnClickListener(v -> {
    //                             if (isCheckNet) {
    //                                 if (!NetUtils.isNetworkAvailable(v.getContext())) {
    //                                     Toast.makeText(v.getContext(), "网络不可用", Toast.LENGTH_SHORT).show();
    //                                     return;
    //                                 }
    //                             }
    //                             try {
    //                                 ReflectionUtil.invoke(method, object, v);
    //                             } catch (Exception e) {
    //                                 e.printStackTrace();
    //                             }
    //                         });
    //                     }
    //                 }
    //             }
    //         }
    //     }
    // }
    //
    // /**
    //  * 注入长按点击事件
    //  *
    //  * @param viewManager
    //  * @param object
    //  */
    // private static void injectOnLongClick(ViewManager viewManager, Object object) {
    //     Class<?> clazz = object.getClass();
    //     Method[] methods = clazz.getDeclaredMethods();
    //     if (methods != null) {
    //         for (Method method : methods) {
    //             OnLongClick onLongClick = method.getAnnotation(OnLongClick.class);
    //             if (onLongClick != null) {
    //                 int[] viewIds = onLongClick.value();
    //                 for (int viewId : viewIds) {
    //                     View view = viewManager.findViewById(viewId);
    //                     //检查网络
    //                     boolean isCheckNet = method.getAnnotation(CheckNet.class) != null;
    //                     if (view != null) {
    //                         view.setOnLongClickListener(v -> {
    //                             if (isCheckNet) {
    //                                 if (!NetUtils.isNetworkAvailable(v.getContext())) {
    //                                     Toast.makeText(v.getContext(), "网络不可用", Toast.LENGTH_SHORT).show();
    //                                     return true;
    //                                 }
    //                             }
    //                             try {
    //                                 ReflectionUtil.invoke(method, object, v);
    //                             } catch (Exception e) {
    //                                 e.printStackTrace();
    //                             }
    //                             return true;
    //                         });
    //                     }
    //                 }
    //             }
    //         }
    //     }
    // }
}
