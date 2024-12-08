package com.ypp.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ypp.dto.ResponseResult;
import ypp.request.OrderDTO;

@FeignClient("service-order")
public interface ServiceOrderClient {
    @RequestMapping(method = RequestMethod.POST,value = "/order/add")
    public ResponseResult orderAdd(@RequestBody OrderDTO orderDTO);
}
