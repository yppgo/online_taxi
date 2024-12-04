package com.ypp.service;

import com.ypp.remote.ServicePrice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ypp.dto.ResponseResult;
import ypp.request.ForecastPriceDTO;
import ypp.response.ForcastPriceResult;

@Service
@Slf4j
public class ForecastPriceService {
    @Autowired
    ServicePrice servicePrice;
    public ResponseResult forecastPrice(String depLongitude,String depLatitude,String desLongitude,String desLatitude){
        log.info("起点经度为："+ depLongitude);
        log.info("起点纬度为："+ depLatitude);
        log.info("终点经度为："+ desLongitude);
        log.info("终点经度为："+ desLatitude);
        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDesLongitude(desLongitude);
        forecastPriceDTO.setDesLatitude(desLatitude);
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDepLongitude(depLongitude);
        ResponseResult<ForcastPriceResult> forcast = servicePrice.getPrice(forecastPriceDTO);
        double price = forcast.getData().getPrice();
        ForcastPriceResult forcastPriceResult = new ForcastPriceResult();
        forcastPriceResult.setPrice(price);
        return ResponseResult.success(forcastPriceResult);
    }
}
