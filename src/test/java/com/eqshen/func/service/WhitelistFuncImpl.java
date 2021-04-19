package com.eqshen.func.service;

import com.eqshen.func.annotation.MultiFunc;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StringUtils;

/**
 * @author eqshen
 * @description
 * @date 2021/4/14
 */
@MultiFunc(name = "whitelist")
public class WhitelistFuncImpl implements IMultiFuncService{

    @Override
    public boolean funcCore(Object param) {
        if(param.equals("1007")){
            return true;
        }
        return false;
    }

    @Override
    public String returnResult() {
        return "{'code':1001,'msg':'白名单拦截'}";
    }
}
