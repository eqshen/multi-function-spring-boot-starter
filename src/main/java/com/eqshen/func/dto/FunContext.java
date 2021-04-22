package com.eqshen.func.dto;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author eqshen
 * @description
 * @date 2021/4/21
 */
public class FunContext {
    /**
     * 被 com.eqshen.func.annotation.ApplyFunc 标记的方法
     */
    private Method method;

    /**
     * 被 ApplyFunc标记的方法入参，key = 参数名
     */
    private Map<String, Object> paramsMap;

    public FunContext(Method method,Map<String, Object> paramsMap){
        this.method = method;
        this.paramsMap = paramsMap;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Map<String, Object> getParamsMap() {
        return paramsMap;
    }

    public void setParamsMap(Map<String, Object> paramsMap) {
        this.paramsMap = paramsMap;
    }
}
