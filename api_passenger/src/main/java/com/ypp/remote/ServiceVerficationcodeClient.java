package com.ypp.remote;

import feign.Request;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ypp.dto.ResponseResult;
import ypp.response.NumberCodeResult;

import java.lang.reflect.Method;

@FeignClient("service-verfication")
public interface ServiceVerficationcodeClient {
    @RequestMapping(method = RequestMethod.GET,value = "/numberCode/{size}")
    ResponseResult<NumberCodeResult> getNumberCode(@PathVariable("size")int size);
}
