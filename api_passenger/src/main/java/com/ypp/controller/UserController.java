package com.ypp.controller;

import com.ypp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ypp.dto.ResponseResult;

import javax.servlet.http.HttpServletRequest;

//根据接收的用户信息进行查询
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/users")
    public ResponseResult getUser(HttpServletRequest request){
        //获取请求的token
       String token =  request.getHeader("Authorization");

        return userService.getUserByToken(token);
    }
}
