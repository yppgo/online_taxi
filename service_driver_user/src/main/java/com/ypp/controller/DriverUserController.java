package com.ypp.controller;

import com.ypp.service.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ypp.constant.DriverCarConstants;
import ypp.dto.DriverUser;
import ypp.dto.ResponseResult;
import ypp.response.DriverUserExistsResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
public class DriverUserController {
    @Autowired
    DriverUserService driverUserService;
    //添加司机信息
    @PostMapping("/user")
    public ResponseResult add_user(@RequestBody DriverUser driverUser){

        return driverUserService.addUser(driverUser);
    }
    //修改司机信息
    @PutMapping("/user")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser){

        return driverUserService.updateService(driverUser);
    }
    //查找司机信息
    @GetMapping("/check_drive/{driverPhone}")
    public ResponseResult<DriverUser> getUser(@PathVariable("driverPhone") String driverPhone){
        ResponseResult<DriverUser> user = driverUserService.getUser(driverPhone);
        DriverUserExistsResponse driverUserExistsResponse = new DriverUserExistsResponse();
        DriverUser DriverUerDb = user.getData();
        int isExist = DriverCarConstants.DRIVER_EXISTS;
        if(DriverUerDb == null){
            isExist = DriverCarConstants.DRIVER_NOT_EXISTS;
            driverUserExistsResponse.setDriverPhone(driverPhone);
            driverUserExistsResponse.setIsExist(isExist);
        }else{
            driverUserExistsResponse.setDriverPhone(driverPhone);
            driverUserExistsResponse.setIsExist(isExist);
        }

        return ResponseResult.success(driverUserExistsResponse);

    }


}
