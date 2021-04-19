package com.eqshen.func.annotation;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * 标记功能实现类
 * @author eqshen
 * @description
 * @date 2021/4/14
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component
public @interface MultiFunc {
    String name() default "";
}
