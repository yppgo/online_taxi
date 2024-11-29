package com.ypp.service;

import com.ypp.remote.MapDirectionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ypp.dto.ResponseResult;
import ypp.response.DirectionResponse;


@Service
public class DirectionService {
    @Autowired
    MapDirectionClient mapDirectionClient;
    public ResponseResult driving(String depLongitude,String depLatitude,String desLongitude,String desLatitude){
        DirectionResponse direction = mapDirectionClient.direction(depLongitude, depLatitude, desLongitude, desLatitude);
        return ResponseResult.success(direction);
    }
}
