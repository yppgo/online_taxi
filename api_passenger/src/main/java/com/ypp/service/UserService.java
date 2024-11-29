package com.ypp.service;

import com.ypp.remote.ServicePassengerUserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ypp.dto.PassengerUser;
import ypp.dto.ResponseResult;
import ypp.dto.TokenResult;
import ypp.utils.JwtUtils;

@Service
@Slf4j
public class UserService {
    @Autowired
    ServicePassengerUserClient servicePassengerUserClient;
    public ResponseResult getUserByToken(String accessToken){
        //根据token去除用户手机号码
        log.info("accessToken："+accessToken);
        TokenResult tokenResult = JwtUtils.checkToken(accessToken);
        String phone = tokenResult.getPhone();
        log.info("passengerPhone："+phone);
        ResponseResult<PassengerUser> user = servicePassengerUserClient.getUser(phone);
        return user;
    }
}
