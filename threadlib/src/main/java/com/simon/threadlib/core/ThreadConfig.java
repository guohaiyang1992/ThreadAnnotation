package com.simon.threadlib.core;

import com.simon.threadlib.annotation.UiThread;
import com.simon.threadlib.utils.ThreadUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * description:  线程 配置
 * autour: Simon
 * created at 2017/6/27 下午8:48
 */
@Aspect
public class ThreadConfig {
    @Pointcut("execution(@com.simon.threadlib.annotation.UiThread * *(..))")
    public void uiMethod() {
    }


    @Around("uiMethod()")
    public void onUiMethodAround(final ProceedingJoinPoint joinPoint) throws Throwable {
        //ui线程运行
        ThreadUtils.runInUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }, getUiDelayTime(joinPoint));//end
    }

    @Pointcut("execution(@com.simon.threadlib.annotation.Background * *(..))")
    public void backgroundMethod() {
    }


    @Around("backgroundMethod()")
    public void onBackgroundAround(final ProceedingJoinPoint joinPoint) throws Throwable {
        //back线程运行
        ThreadUtils.runInBackGroundThread(new Runnable() {
            @Override
            public void run() {
                try {
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });//end
    }

    public int getUiDelayTime(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        String methodName = pjp.getSignature().getName();
        Class<?> classTarget = pjp.getTarget().getClass();
        Class<?>[] par = ((MethodSignature) pjp.getSignature()).getParameterTypes();
        Method objMethod = classTarget.getDeclaredMethod(methodName, par);//原方法：getMethod ,当前方法可以获取私有方法
        objMethod.setAccessible(true);//设置此处 可以使用私有方法
        UiThread uiThread = objMethod.getAnnotation(UiThread.class);
        return uiThread.delayTime();
    }
}
