package com.ypp.service;

import com.ypp.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ypp.dto.Car;
import ypp.dto.ResponseResult;

@Service
public class CarService {
    @Autowired
    CarMapper carMapper;
    public ResponseResult addCar(Car car){
        carMapper.insert(car);
        return ResponseResult.success("");
    }
}
