package com.ypp.controller;

import com.ypp.service.ForecastPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ypp.dto.ResponseResult;
import ypp.request.ForecastPriceDTO;


@RestController
public class forecastPriceController {
    @Autowired
    private ForecastPriceService forecastPriceService;
    @PostMapping("/forecast_price")
    public ResponseResult foreCastPrice(@RequestBody ForecastPriceDTO forecastPriceDTO){
        String depLongitude = forecastPriceDTO.getDepLongitude();
        String depLatitude = forecastPriceDTO.getDepLatitude();
        String desLongitude = forecastPriceDTO.getDesLongitude();
        String desLatitude = forecastPriceDTO.getDesLatitude();
        return forecastPriceService.forecastPrice(depLongitude,depLatitude,desLongitude,desLatitude);
    }
}

