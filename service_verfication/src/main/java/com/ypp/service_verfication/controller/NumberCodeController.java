package com.ypp.service_verfication.controller;

import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ypp.dto.ResponseResult;
import ypp.response.NumberCodeResult;

import javax.websocket.server.PathParam;

@RestController
public class NumberCodeController {
    @GetMapping("/numberCode/{size}")
    public  ResponseResult numberCode(@PathVariable("size") int size){
        System.out.println("size为："+size);
        //生成验证码
        double num = (Math.random()*9 + 1) * Math.pow(10,size - 1);
        int resultInt = (int)num;
        //设置返回对象
        NumberCodeResult res = new NumberCodeResult();
        res.setNumberCode(resultInt);
        return ResponseResult.success(res);
    }
}
