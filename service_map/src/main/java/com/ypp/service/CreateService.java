package com.ypp.service;

import com.ypp.remote.ServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ypp.dto.ResponseResult;

@Service
public class CreateService {
    @Autowired
    ServiceClient serviceClient;
    public ResponseResult addService(String name){

        return serviceClient.addService(name);
    }
}
