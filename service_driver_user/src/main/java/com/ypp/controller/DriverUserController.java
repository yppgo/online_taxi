package com.ypp.controller;

import com.ypp.service.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ypp.dto.DriverUser;
import ypp.dto.ResponseResult;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
public class DriverUserController {
    @Autowired
    DriverUserService driverUserService;
    @PostMapping("/user")
    public ResponseResult add_user(@RequestBody DriverUser driverUser){

        return driverUserService.addUser(driverUser);
    }
    @PutMapping("/user")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser){

        return driverUserService.updateService(driverUser);
    }


}
