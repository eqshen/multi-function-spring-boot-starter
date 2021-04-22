package com.eqshen.func.service;

import com.eqshen.func.dto.FunContext;
import org.aspectj.lang.ProceedingJoinPoint;

import javax.annotation.Nullable;

/**
 * @author eqshen
 * @description
 * @date 2021/4/14
 */
public interface IMultiFuncService {


    /**
     * 判断是否需要拦截
     * @param param 参数信息
     * @param context 被aop方法的上下文信息，以便能支持更多功能
     * @return false = 拦截
     */
    boolean funcCore(@Nullable Object param, FunContext context);

    /**
     * 拦截后返回的结果
     * @return
     */
    String returnResult();
}
