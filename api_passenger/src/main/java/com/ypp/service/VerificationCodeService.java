package com.ypp.service;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeService {
    public String generateCode(String passengerPhone){
        //1.生成验证码
        //2.保存到redis
        JSONObject result = new JSONObject();
        result.put("code",1);
        result.put("message","success");
        return result.toString();
    }
}
