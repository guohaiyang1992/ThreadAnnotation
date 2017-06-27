package com.simon.threadlib.core;

import android.text.TextUtils;
import android.util.Log;

import com.simon.threadlib.annotation.MethodWatch;
import com.simon.threadlib.annotation.UiThread;
import com.simon.threadlib.utils.StopWatch;
import com.simon.threadlib.utils.ThreadUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * description: 方法观察
 * autour: Simon
 * created at 2017/6/27 下午8:49
 */
@Aspect
public class MethodConfig {
    @Pointcut("execution(@com.simon.threadlib.annotation.MethodWatch * *(..))")
    public void methodWatch() {
    }


    @Around("methodWatch()")
    public void onMethodWatchAround(final ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(getMethodName(joinPoint));
        joinPoint.proceed();
        stopWatch.stop();
        Log.v(getTag(joinPoint), stopWatch.prettyPrint());
    }

    public String getTag(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        String methodName = pjp.getSignature().getName();
        Class<?> classTarget = pjp.getTarget().getClass();
        Class<?>[] par = ((MethodSignature) pjp.getSignature()).getParameterTypes();
        Method objMethod = classTarget.getDeclaredMethod(methodName, par);//原方法：getMethod ,当前方法可以获取私有方法
        objMethod.setAccessible(true);//设置此处 可以使用私有方法
        MethodWatch methodWatch = objMethod.getAnnotation(MethodWatch.class);
        String result = methodWatch.tag();
        if (TextUtils.isEmpty(result)) {
            result = methodName;
        }
        return result;
    }

    public String getMethodName(ProceedingJoinPoint pjp) {
        return pjp.getSignature().getName();
    }
}
