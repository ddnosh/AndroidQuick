package la.xiong.androidquick.demo.features.function.annotation.manager;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;

public class InjectManager {

    public static void inject(Activity activity) {
        inject(new ViewManager(activity), activity);
    }

    public static void inject(Fragment fragment) {
        inject(new ViewManager(fragment), fragment);
    }

    public static void inject(View view) {
        inject(new ViewManager(view), view);
    }

    private static void inject(ViewManager viewManager, Object object) {
        InjectManagerService.injectContentView(viewManager, object);
        InjectManagerService.injectFieldById(viewManager, object);
        // InjectManagerService.injectEvent(viewManager, object);
    }

}
