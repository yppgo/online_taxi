package com.ypp.controller;

import com.ypp.service.DriverUserService;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ypp.dto.DriverUser;
import ypp.dto.ResponseResult;

@RestController
public class DriverUserController {
    @Autowired
    DriverUserService driverUserService;
    @RequestMapping("/driver_user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser){
        return driverUserService.addDriverUser(driverUser);
    }
    @PutMapping("/driver_user")
    public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser){
        return driverUserService.updateDriverUser(driverUser);
    }
}
