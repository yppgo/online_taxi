package com.ypp.service;

import com.ypp.mapper.DriverUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ypp.constant.CommonResponseStatus;
import ypp.constant.DriverCarConstants;
import ypp.dto.DriverUser;
import ypp.dto.DriverUserWorkStatus;
import ypp.dto.ResponseResult;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DriverUserService {
    @Autowired
    DriverUserMapper driverUserMapper;
    public ResponseResult addUser(DriverUser driverUser) {
        int insert = driverUserMapper.insert(driverUser);

        //初始化司机状态
        DriverUserWorkStatus status = new DriverUserWorkStatus();
        status.setDriverId(driverUser.getId());
        status.setWorkStatus(DriverCarConstants.DRIVER_WORK_STATUS_START);
        status.setGmtCreate(LocalDateTime.now());
        status.setGmtModified(LocalDateTime.now());
        return ResponseResult.success("");
    }

    public ResponseResult updateService(DriverUser driverUser) {
        LocalDateTime now = LocalDateTime.now();
        driverUser.setGmtCreate(now);
        driverUser.setGmtModified(now);
        driverUserMapper.updateById(driverUser);
        return ResponseResult.success("driverUser");
    }
    //
    public ResponseResult<DriverUser> getUser(String driverPhone){
        Map<String,Object> map = new HashMap<>();
        map.put("driver_phone",driverPhone);
        map.put("state", DriverCarConstants.DRIVER_STATE_VALID);
        List<DriverUser> driverUsers = driverUserMapper.selectByMap(map);
        if(driverUsers.isEmpty()){
            return ResponseResult.fail(CommonResponseStatus.DRIVER_NOT_EXIST.getCode(),CommonResponseStatus.DRIVER_NOT_EXIST.getValue());
        }
        DriverUser driverUser = driverUsers.get(0);
        return ResponseResult.success(driverUser);
    }
}
