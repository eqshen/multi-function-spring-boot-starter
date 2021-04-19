package com.eqshen.func.service;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author eqshen
 * @description
 * @date 2021/4/14
 */
public interface IMultiFuncService {


    /**
     * 判断是否需要拦截
     * @param param 参数信息
     * @return
     */
    boolean funcCore(Object param);

    /**
     * 拦截后返回的结果
     * @return
     */
    String returnResult();
}
