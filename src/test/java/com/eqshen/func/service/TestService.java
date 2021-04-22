package com.eqshen.func.service;

/**
 * 普通的服务类，用于演示功能的使用
 * @author eqshen
 * @description
 * @date 2021/4/19
 */

import com.alibaba.fastjson.JSONObject;
import com.eqshen.func.annotation.ApplyFunc;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    //声明注解，以及启用的拓展方法，paramName 是IMultiFuncService 需要用到的参数名
    @ApplyFunc(funName = "whitelist",paramName = "userId")
    @ApplyFunc(funName = "rateLimit")
    public JSONObject getUserInfo(String userId,String otherInfo){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",userId);
        jsonObject.put("name","tom");
        jsonObject.put("age",18);
        return jsonObject;
    }


}
