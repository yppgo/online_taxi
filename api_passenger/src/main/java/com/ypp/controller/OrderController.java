package com.ypp.controller;

import com.ypp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ypp.dto.ResponseResult;
import ypp.request.OrderDTO;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping("/add")
    public ResponseResult orderAdd(@RequestBody OrderDTO orderDTO){
        System.out.println("订单信息："+orderDTO);
        return orderService.orderAdd(orderDTO);
    }
}
