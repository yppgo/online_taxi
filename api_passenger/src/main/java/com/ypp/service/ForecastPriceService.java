package com.ypp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ypp.dto.ResponseResult;
import ypp.request.ForecastPriceDTO;
import ypp.response.ForcastPriceResult;

@Service
@Slf4j
public class ForecastPriceService {
    public ResponseResult forecastPrice(String depLongitude,String depLatitude,String desLongitude,String desLatitude){
        log.info("起点经度为："+ depLongitude);
        log.info("起点纬度为："+ depLatitude);
        log.info("终点经度为："+ desLongitude);
        log.info("终点经度为："+ desLatitude);
        ForcastPriceResult forcastPriceResult = new ForcastPriceResult();
        forcastPriceResult.setPrice(12.2);
        return ResponseResult.success(forcastPriceResult);
    }
}
