package com.ypp.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ypp.dto.ResponseResult;
import ypp.response.NumberCodeResult;

@FeignClient("service-verfication")
public interface VerficationCodeClient {
    @RequestMapping(method = RequestMethod.GET,value = "/numberCode/{size}")
    public ResponseResult<NumberCodeResult> getVerficationCode(@PathVariable("size") int size);
}
