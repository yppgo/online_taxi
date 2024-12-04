package com.ypp.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ypp.dto.DriverUser;
import ypp.dto.ResponseResult;

@FeignClient("service-driver-user")
public interface DriverUserClient {
    @RequestMapping(method = RequestMethod.POST,value = "/user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser);

    @RequestMapping(method = RequestMethod.PUT,value = "/user")
    public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser);
}
