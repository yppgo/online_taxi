package com.ypp.controller;

import com.ypp.service.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ypp.dto.ResponseResult;
import ypp.request.ForecastPriceDTO;

@RestController
@RequestMapping("/direction")
public class DirectionController {
    @Autowired
    DirectionService directionService;
    @GetMapping("/driving")
    public ResponseResult driving(@RequestBody ForecastPriceDTO forecastPriceDTO){
        String depLongitude = forecastPriceDTO.getDepLongitude();
        String depLatitude = forecastPriceDTO.getDepLatitude();
        String desLongitude = forecastPriceDTO.getDesLongitude();
        String desLatitude = forecastPriceDTO.getDesLatitude();
        return directionService.driving(depLongitude,depLatitude,desLongitude,desLatitude);
    }
}
