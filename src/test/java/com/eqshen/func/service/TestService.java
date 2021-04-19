package com.eqshen.func.service;

/**
 * @author eqshen
 * @description
 * @date 2021/4/19
 */

import com.alibaba.fastjson.JSONObject;
import com.eqshen.func.annotation.ApplyFunc;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @ApplyFunc(funName = "whitelist",paramName = "userId")
    public JSONObject getUserInfo(String userId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",userId);
        jsonObject.put("name","tom");
        jsonObject.put("age",18);
        return jsonObject;
    }
}
