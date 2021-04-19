package com.eqshen.func.aop;

import com.alibaba.fastjson.JSONObject;
import com.eqshen.func.annotation.ApplyFunc;
import com.eqshen.func.config.FuncRegistry;
import com.eqshen.func.service.IMultiFuncService;
import com.eqshen.func.util.ReflectionUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author eqshen
 * @description
 * @date 2021/4/14
 */
@Aspect
@Component

public class FuncAopProcessor {

    private static Logger log = LoggerFactory.getLogger(FuncAopProcessor.class);

    @Pointcut("@annotation(com.eqshen.func.annotation.ApplyFunc)")
    public void aopPoint(){
    }

    @Around("aopPoint()")
    public Object doRoute(ProceedingJoinPoint jp) throws Throwable{
        //获取方法上 ApplyMultiFunc 注解，并拿到对应的处理bean（IMultiFuncService实现类）
        final Method method = ReflectionUtil.getMethod(jp);
        final Map<String, Object> paramsMap = ReflectionUtil.getParamsNameAndValue(jp);
        final ApplyFunc applyFunc = method.getAnnotation(ApplyFunc.class);
        final Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            if(! (annotation  instanceof ApplyFunc)){
                continue;
            }
            String funcName = ((ApplyFunc) annotation).funName();
            final String paramName = ((ApplyFunc) annotation).paramName();
            final Object paramValue = paramsMap.get(paramName);
            if(paramValue == null){
                continue;
            }
            final IMultiFuncService multiFuncService = FuncRegistry.getByName(funcName);
            if(multiFuncService == null){
                log.warn("funcName:{} function does not exist!",funcName );
                continue;
            }
            //调用
            final boolean condition = multiFuncService.funcCore(paramValue);
            if(!condition){
                //阻断返回
                Class<?> returnType = method.getReturnType();
                String returnResult = multiFuncService.returnResult();

                if(StringUtils.isEmpty(returnResult)){
                    return returnType.newInstance();
                }else{
                    return JSONObject.parseObject(returnResult, returnType);
                }
            }
        }
        //正常返回
        return jp.proceed();
    }


}
