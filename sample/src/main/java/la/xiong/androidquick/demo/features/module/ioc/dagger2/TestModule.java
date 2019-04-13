package la.xiong.androidquick.demo.features.module.ioc.dagger2;

import dagger.Module;
import dagger.Provides;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@Module
public class TestModule {//module is only used when the class couldn't be marked as @Inject, such as the third library class

    @Provides
    public Dagger2Test2Bean getBean() {
        return new Dagger2Test2Bean("Mike");
    }
}
