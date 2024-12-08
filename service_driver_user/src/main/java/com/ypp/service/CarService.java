package com.ypp.service;

import com.ypp.mapper.CarMapper;
import com.ypp.remote.ServiceMapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ypp.dto.Car;
import ypp.dto.ResponseResult;
import ypp.response.TrackResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarService {
    @Autowired
    CarMapper carMapper;
    @Autowired
    private ServiceMapClient mapClient;
    public ResponseResult addCar(Car car){
        carMapper.insert(car);
        ResponseResult responseResult = mapClient.addTerminal(car.getVehicleNo(),car.getId()+"");
        Object object = responseResult.getData();
        String tid = object.toString();
        System.out.println(tid);
        car.setTid(tid);
        //获取车辆对应的tid
        ResponseResult responseResult1 = mapClient.addTrack(car.getTid());
        Object data = responseResult1.getData();
        TrackResponse data1 = (TrackResponse)data;
        String trid = data1.getTrid();
        String trname = data1.getTrname();
        car.setTrid(trid);
        car.setTrname(trname);
        carMapper.updateById(car);
        return ResponseResult.success("");
    }
    public ResponseResult<Car> getCarById(Long carId){
        Map<String,Object> map = new HashMap<>();
        map.put("id",carId);
        List<Car> cars = carMapper.selectByMap(map);
        return ResponseResult.success(cars.get(0));

    }
}
