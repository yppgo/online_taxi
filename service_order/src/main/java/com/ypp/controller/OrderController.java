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
    public ResponseResult addOrder(@RequestBody OrderDTO orderDTO){
        return orderService.orderAdd(orderDTO);
    }
}
