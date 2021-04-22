package com.eqshen.func.funImpl;

import com.eqshen.func.annotation.MultiFunc;
import com.eqshen.func.dto.FunContext;
import com.eqshen.func.service.IMultiFuncService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StringUtils;

/**
 * 白名单过滤
 * @author eqshen
 * @description
 * @date 2021/4/14
 */
@MultiFunc(name = "whitelist")
public class WhitelistFuncImpl implements IMultiFuncService {

    @Override
    public boolean funcCore(Object param, FunContext context) {
        if(param.equals("1007")){
            return true;
        }
        return false;
    }

    @Override
    public String returnResult() {
        return "{'code':1001,'msg':'非白名单用户，拒绝访问'}";
    }
}
