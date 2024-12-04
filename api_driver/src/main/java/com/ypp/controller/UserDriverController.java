package com.ypp.controller;

import com.ypp.service.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ypp.dto.Car;
import ypp.dto.DriverCarBindingRelationship;
import ypp.dto.DriverUser;
import ypp.dto.ResponseResult;

@RestController
public class UserDriverController {
    @Autowired
    DriverUserService userServiceController;
    @PutMapping("/user")
    public ResponseResult updaeDriver(@RequestBody DriverUser driverUser){
        return userServiceController.updateDriverUser(driverUser);
    }
    @PostMapping("/car")
    public ResponseResult addCar(@RequestBody Car car){
        return userServiceController.addCar(car);
    }
}
