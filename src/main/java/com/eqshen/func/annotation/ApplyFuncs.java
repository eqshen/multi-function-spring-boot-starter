package com.eqshen.func.annotation;

import java.lang.annotation.*;

/**
 * @author eqshen
 * @description
 * @date 2021/4/19
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ApplyFuncs {
    ApplyFunc[] value();
}
