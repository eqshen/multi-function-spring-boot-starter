package com.eqshen.func.funImpl;

import com.eqshen.func.annotation.MultiFunc;
import com.eqshen.func.dto.FunContext;
import com.eqshen.func.service.IMultiFuncService;
import com.eqshen.func.util.SlideWindowRateLimiter;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 限流
 * 为了演示功能且不增加额外依赖,简单自己实现
 * @author eqshen
 * @description
 * @date 2021/4/21
 */
@MultiFunc(name = "rateLimit")
public class RateLimitServiceImpl implements IMultiFuncService {


    private Map<String,SlideWindowRateLimiter> rateLimiterMap;

    public RateLimitServiceImpl(){
        rateLimiterMap = new HashMap<>();
    }

    @Override
    public boolean funcCore(Object param, FunContext context) {
        final Method method = context.getMethod();
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();
        String key = className + "#" +  methodName;
        final SlideWindowRateLimiter rateLimiter = rateLimiterMap.getOrDefault(key, getDefaultRateLimiter());
        rateLimiterMap.put(key, rateLimiter);
        return rateLimiter.access();
    }

    @Override
    public String returnResult() {
        return "{'code':1002,'msg':'访问频率超过限制，请稍后再试'}";
    }

    private SlideWindowRateLimiter getDefaultRateLimiter(){
        //5秒的时间窗口内最多5个请求
        return new SlideWindowRateLimiter(5,1000,5);
    }
}

