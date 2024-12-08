package com.ypp.service;

import com.ypp.remote.DriverUserClient;
import com.ypp.remote.ServiceMapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.script.ReactiveScriptExecutor;
import org.springframework.stereotype.Service;
import ypp.dto.Car;
import ypp.dto.ResponseResult;
import ypp.request.ApiDriverPointRequest;
import ypp.request.PointRequest;

@Service
public class PointService {
    @Autowired
    DriverUserClient driverUserClient;
    @Autowired
    ServiceMapClient serviceMapClient;
    public ResponseResult pointUpload( ApiDriverPointRequest apiDriverPointRequest){
        //获取carid
        ResponseResult<Car> car = driverUserClient.getCarById(apiDriverPointRequest.getCarId());
        //通过carid获取tid和trid
        Car data = car.getData();
        String tid = data.getTid();
        String trid = data.getTrid();

        //上传到地图上
        PointRequest pointRequest = new PointRequest();
        pointRequest.setTid(tid);
        pointRequest.setTrid(trid);
        pointRequest.setPoints(apiDriverPointRequest.getPoints());
        return serviceMapClient.pointUpload(pointRequest);

    }
}
