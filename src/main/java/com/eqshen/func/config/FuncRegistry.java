package com.eqshen.func.config;

import com.eqshen.func.service.IMultiFuncService;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author eqshen
 * @description
 * @date 2021/4/19
 */
public class FuncRegistry {
    private static ConcurrentHashMap<String, IMultiFuncService> map;

    static {
        map = new ConcurrentHashMap<>();
    }

    public static IMultiFuncService getByName(String funcName){
        return map.get(funcName);
    }

    public static void register(String funcName,IMultiFuncService instance){
        map.put(funcName,instance);
    }
}
