package com.ypp.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ypp.dto.PassengerUser;
import ypp.dto.ResponseResult;
import ypp.request.VerficationCodeDTO;
import ypp.response.NumberCodeResult;

@FeignClient("service-passenger-user")
public interface ServicePassengerUserClient {
    @RequestMapping(method = RequestMethod.POST,value = "/user")
    public ResponseResult loginOrRegister(@RequestBody VerficationCodeDTO verficationCodeDTO);
    @RequestMapping(method = RequestMethod.GET,value = "/user/{phone}")
    public ResponseResult<PassengerUser>getUser(@PathVariable("phone") String phone);
}
