package com.eqshen.func.annotation;

import java.lang.annotation.*;

/**
 * 标记方法，开启功能
 * @author eqshen
 * @description
 * @date 2021/4/14
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(ApplyFuncs.class)
public @interface ApplyFunc {
    /**
     * 拓展函数名称，按照数组中顺序调用
     * @return
     */
    String funName() default "";

    String paramName() default "";
}
