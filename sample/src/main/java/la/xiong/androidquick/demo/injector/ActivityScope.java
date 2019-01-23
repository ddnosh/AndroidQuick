package la.xiong.androidquick.demo.injector;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface ActivityScope {
}
