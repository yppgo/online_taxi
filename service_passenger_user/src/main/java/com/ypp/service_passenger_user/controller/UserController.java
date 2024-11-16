package com.ypp.service_passenger_user.controller;

import com.ypp.service_passenger_user.service.UserService;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ypp.dto.ResponseResult;
import ypp.request.VerficationCodeDTO;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/user")
    public ResponseResult loginOrRegister(@RequestBody VerficationCodeDTO verficationCodeDTO){
        String passengerPhone = verficationCodeDTO.getPassengerPhone();
        System.out.println("用户手机号码为："+passengerPhone);
        return userService.loginOrRegister(passengerPhone);
    }
}
