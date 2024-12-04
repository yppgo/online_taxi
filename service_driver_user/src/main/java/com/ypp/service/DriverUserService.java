package com.ypp.service;

import com.ypp.mapper.DriverUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ypp.dto.DriverUser;
import ypp.dto.ResponseResult;

import java.time.LocalDateTime;

@Service
public class DriverUserService {
    @Autowired
    DriverUserMapper driverUserMapper;
    public ResponseResult addUser(DriverUser driverUser) {
        int insert = driverUserMapper.insert(driverUser);
        return ResponseResult.success("");
    }

    public ResponseResult updateService(DriverUser driverUser) {
        LocalDateTime now = LocalDateTime.now();
        driverUser.setGmtCreate(now);
        driverUser.setGmtModified(now);
        driverUserMapper.updateById(driverUser);
        return ResponseResult.success("driverUser");

    }
}
