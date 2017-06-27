package com.simon.threadlib.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * description: 主线程 注解
 * autour: Simon
 * created at 2017/6/27 下午7:51
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD})
public @interface UiThread {
    public int delayTime() default 0;
}