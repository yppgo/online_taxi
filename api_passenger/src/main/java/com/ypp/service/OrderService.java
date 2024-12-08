package com.ypp.service;

import com.ypp.remote.ServiceOrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ypp.dto.ResponseResult;
import ypp.request.OrderDTO;

@Service
public class OrderService {
    @Autowired
    ServiceOrderClient serviceOrderClient;
    public ResponseResult orderAdd(@RequestBody OrderDTO orderDTO){
       return serviceOrderClient.orderAdd(orderDTO);
    }
}
