package com.ypp.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ypp.dto.ResponseResult;
import ypp.request.ForecastPriceDTO;
import ypp.response.DirectionResponse;


@FeignClient("service-map")
public interface ServiceMapClient {
    @RequestMapping(method = RequestMethod.GET,value = "/direction/driving")
    public ResponseResult<DirectionResponse> direction(@RequestBody ForecastPriceDTO forecastPriceDTO);
}
