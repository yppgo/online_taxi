package com.ypp.service;

import com.ypp.remote.DriverUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ypp.dto.Car;
import ypp.dto.DriverCarBindingRelationship;
import ypp.dto.DriverUser;
import ypp.dto.ResponseResult;

@Service
public class DriverUserService {
    @Autowired
    DriverUserClient driverUserClient;
    public ResponseResult updateDriverUser(DriverUser driverUser) {
        return driverUserClient.updateDriver(driverUser);
    }

    public ResponseResult addCar(Car car) {

        return driverUserClient.addCar(car);
    }
}
