package com.ypp.controller;

import com.ypp.service.CreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ypp.dto.ResponseResult;

@RestController
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    CreateService createService;
    @PostMapping("/add")
    public ResponseResult addService(String name){

        return createService.addService(name);
    }
}

