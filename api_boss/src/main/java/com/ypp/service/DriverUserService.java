package com.ypp.service;

import com.ypp.remote.DriverUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ypp.dto.DriverUser;
import ypp.dto.ResponseResult;

@Service
public class DriverUserService {
    @Autowired
    DriverUserClient driverUserClient;

    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser){

        return driverUserClient.addDriverUser(driverUser);
    }
    public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser){

        return driverUserClient.updateDriverUser(driverUser);
    }
}
