package com.ypp.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ypp.mapper.DriverCarBindingRelationshipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ypp.constant.CommonResponseStatus;
import ypp.constant.DriverCarConstants;
import ypp.dto.DriverCarBindingRelationship;
import ypp.dto.ResponseResult;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DriverCarBindingRelationshipService {
    @Autowired
    DriverCarBindingRelationshipMapper driverCarBindingRelationshipMapper;
    public ResponseResult bind(DriverCarBindingRelationship driverCarBindingRelationship){
        //过滤重复绑定
        QueryWrapper<DriverCarBindingRelationship> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_id",driverCarBindingRelationship.getDriverId());
        queryWrapper.eq("car_id",driverCarBindingRelationship.getCarId());
        queryWrapper.eq("bind_state",DriverCarConstants.DRIVER_CAR_BIND);
        Integer integer = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if(integer.intValue() > 0){
            return ResponseResult.fail(CommonResponseStatus.DRIVER_CAR_BIND_EXISTS.getCode(),CommonResponseStatus.DRIVER_CAR_BIND_EXISTS.getValue());
        }
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_id",driverCarBindingRelationship.getDriverId());
        queryWrapper.eq("bind_state",DriverCarConstants.DRIVER_CAR_BIND);
        integer = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if(integer.intValue() > 0){
            return ResponseResult.fail(CommonResponseStatus.DRIVER_BIND_EXISTS.getCode(),CommonResponseStatus.DRIVER_BIND_EXISTS.getValue());
        }
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("car_id",driverCarBindingRelationship.getCarId());
        queryWrapper.eq("bind_state",DriverCarConstants.DRIVER_CAR_BIND);
        integer = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if(integer.intValue() > 0){
            return ResponseResult.fail(CommonResponseStatus.CAR_BIND_EXISTS.getCode(),CommonResponseStatus.CAR_BIND_EXISTS.getValue());
        }

        driverCarBindingRelationship.setBindState(DriverCarConstants.DRIVER_CAR_BIND);
        driverCarBindingRelationshipMapper.insert(driverCarBindingRelationship);
        return ResponseResult.success("");
    }

    public ResponseResult unbind(DriverCarBindingRelationship driverCarBindingRelationship) {
        Map<String,Object> map = new HashMap<>();
        map.put("driver_id",driverCarBindingRelationship.getDriverId());
        map.put("car_id",driverCarBindingRelationship.getCarId());
        map.put("bind_state",DriverCarConstants.DRIVER_CAR_BIND);

        List<DriverCarBindingRelationship> driverCarBindingRelationships = driverCarBindingRelationshipMapper.selectByMap(map);
        //不存在报错
        if(driverCarBindingRelationships.isEmpty()){
            return ResponseResult.fail(CommonResponseStatus.DRIVER_CAR_BIND_EXISTS.getCode(),CommonResponseStatus.DRIVER_CAR_BIND_EXISTS.getValue());
        }
        //检查数据库中是否存在数据
        DriverCarBindingRelationship driverCarBindingRelationship1 = driverCarBindingRelationships.get(0);
        driverCarBindingRelationship1.setBindState(DriverCarConstants.DRIVER_CAR_UNBIND);
        driverCarBindingRelationship1.setUnBindingTime(LocalDateTime.now());
        //存在解绑
        driverCarBindingRelationshipMapper.updateById(driverCarBindingRelationship1);
        return ResponseResult.success("");
    }
}
