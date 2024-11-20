package com.ypp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ypp.dto.ResponseResult;

@RestController
public class testController {
    @GetMapping("/test")
    public String test(){
        return "test";
    }
    @GetMapping("/authTest")
    public ResponseResult authTest() {
        return ResponseResult.success("auth test");
    }
    @GetMapping("noauthTest")
    public ResponseResult noauthTest(){
        return ResponseResult.success("noauth test");
    }

}
