package com.ypp.remote;

import org.bouncycastle.crypto.agreement.jpake.JPAKEUtil;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ypp.dto.Car;
import ypp.dto.DriverCarBindingRelationship;
import ypp.dto.DriverUser;
import ypp.dto.ResponseResult;
import ypp.utils.RedisPrefixUtils;

@FeignClient("service-driver-user")
public interface DriverUserClient {
    //远程调用插入司机信息
    @RequestMapping(method = RequestMethod.PUT,value = "/user")
    public ResponseResult updateDriver(@RequestBody DriverUser driverUser);
    //远程调用插入车辆信息
    @RequestMapping(method = RequestMethod.POST,value = "/car")
    public ResponseResult addCar(@RequestBody Car car);

    @RequestMapping(method = RequestMethod.POST,value = "/driver_car_binding_relationship/bind")
    public ResponseResult bind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship);
    //远程调用解绑司机和车辆
    @RequestMapping(method = RequestMethod.POST,value = "/driver_car_binding_relationship/unbind")
    public ResponseResult unBind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship);
}
