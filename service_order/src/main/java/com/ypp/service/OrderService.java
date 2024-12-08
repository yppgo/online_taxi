package com.ypp.service;

import com.ypp.mapper.OrderInfoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ypp.constant.OrderConstant;
import ypp.dto.OrderInfo;
import ypp.dto.ResponseResult;
import ypp.request.OrderDTO;

import java.time.LocalDateTime;

@Service
public class OrderService {
    @Autowired
    OrderInfoMapper orderInfoMapper;
    public ResponseResult orderAdd(@RequestBody OrderDTO orderDTO){
        System.out.println("order_service:"+orderDTO);
        OrderInfo order = new OrderInfo();
        BeanUtils.copyProperties(orderDTO,order);
        order.setOrderStatus(OrderConstant.START);
        LocalDateTime now = LocalDateTime.now();
        order.setGmtModified(now);
        orderInfoMapper.insert(order);
        return ResponseResult.success("订单插入成功");
    }
}
