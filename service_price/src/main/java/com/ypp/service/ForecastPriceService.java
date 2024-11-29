package com.ypp.service;

import com.ypp.remote.ServiceMapClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ypp.dto.ResponseResult;
import ypp.request.ForecastPriceDTO;
import ypp.response.DirectionResponse;
import ypp.response.ForcastPriceResult;

@Service
@Slf4j
public class ForecastPriceService {
    @Autowired
    public ServiceMapClient serviceMapClient;
    public ResponseResult forecastPrice(String depLongitude,String depLatitude,String desLongitude,String desLatitude){
        log.info("起点经度为："+ depLongitude);
        log.info("起点纬度为："+ depLatitude);
        log.info("终点经度为："+ desLongitude);
        log.info("终点经度为："+ desLatitude);
        log.info("调用地图服务查询距离和时常");
        log.info("读取计价规则");
        log.info("计算价格");
        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDesLongitude(desLongitude);
        forecastPriceDTO.setDesLatitude(desLatitude);
        ResponseResult<DirectionResponse> direction = serviceMapClient.direction(forecastPriceDTO);
        log.info("距离："+ direction.getData().getDistance()+"时间：" + direction.getData().getDuration());
        log.info("读取计价规则");
        log.info("计算价格");
        ForcastPriceResult forcastPriceResult = new ForcastPriceResult();
        forcastPriceResult.setPrice(12.2);
        return ResponseResult.success(forcastPriceResult);
    }
}
