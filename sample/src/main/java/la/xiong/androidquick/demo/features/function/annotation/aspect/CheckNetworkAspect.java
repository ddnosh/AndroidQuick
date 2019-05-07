package la.xiong.androidquick.demo.features.function.annotation.aspect;

import android.content.Context;
import android.widget.Toast;

import com.blankj.utilcode.util.NetworkUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class CheckNetworkAspect {
    private static final String TAG = CheckNetworkAspect.class.getSimpleName();

    /**
     * 找到处理的切点
     *   * *(..)  “**”表示是任意包名   “..”表示任意类型任意多个参数
     */
    @Pointcut("execution(@la.xiong.androidquick.demo.features.function.annotation.aspect.CheckNetwork  * *(..))")
    public void executionCheckNetwork() {
    }

    /**
     * 处理切面
     *
     * @param joinPoint
     * @return
     */
    @Around("executionCheckNetwork()")
    public Object checkPermission(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        CheckNetwork annotation = signature.getMethod().getAnnotation(CheckNetwork.class);
        if (annotation != null) {
            Context context = AspectUtils.getContext(joinPoint.getThis());
            if (NetworkUtils.isConnected()) {
                Toast.makeText(context, "当前网络正常", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "此时没有网络连接", Toast.LENGTH_SHORT).show();
            }
            return joinPoint.proceed();
        }
        return null;
    }


}
