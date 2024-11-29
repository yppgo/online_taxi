package com.ypp.service_passenger_user.service;

import com.ypp.service_passenger_user.mapper.PassengerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ypp.constant.CommonResponseStatus;
import ypp.dto.PassengerUser;
import ypp.dto.ResponseResult;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private PassengerUserMapper passengerUserMapper;
    public ResponseResult loginOrRegister(String passengerPhone){
        //根据手机号查询用户信息
        Map<String,Object> map = new HashMap<>();
        map.put("passenger_phone",passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        System.out.println(passengerUsers.size() == 0 ?"无记录":passengerUsers.get(0).getPassengerPhone());
        System.out.println("user-service");
        //如果没有查询到该数据就创建用户并插入数据库中
        if(passengerUsers.size() == 0){
            PassengerUser passengerUser = new PassengerUser();
            passengerUser.setPassengerName("张山");
            passengerUser.setPassengerGender((byte)0);
            passengerUser.setState((byte)0);
            LocalDateTime localDateTime =  LocalDateTime.now();
            passengerUser.setGmtCreate(localDateTime);
            passengerUser.setGmtModified(localDateTime);
            passengerUser.setPassengerPhone(passengerPhone);
            passengerUserMapper.insert(passengerUser);
        }
        return ResponseResult.success();
    }
    public ResponseResult getUserByPhone(String passengerPhone){
        Map<String,Object> map = new HashMap<>();
        map.put("passenger_phone",passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        if(passengerUsers.size() == 0){
            return ResponseResult.fail(CommonResponseStatus.USER_NOT_EXIST.getCode(),CommonResponseStatus.USER_NOT_EXIST.getValue());
        }else{
            PassengerUser passengerUser = passengerUsers.get(0);
            return ResponseResult.success(passengerUser);
        }
    }
}
