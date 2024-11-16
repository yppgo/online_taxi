package com.ypp.service_passenger_user.service;

import org.springframework.stereotype.Service;
import ypp.dto.ResponseResult;

@Service
public class UserService {
    public ResponseResult loginOrRegister(String passengerPhone){
        //根据手机号查询用户信息

        System.out.println("user-service");
        return ResponseResult.success();
    }
}
