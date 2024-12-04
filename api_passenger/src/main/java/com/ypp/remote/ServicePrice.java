package com.ypp.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ypp.dto.ResponseResult;
import ypp.request.ForecastPriceDTO;
import ypp.response.ForcastPriceResult;

@FeignClient("service-price")
public interface ServicePrice {
    @RequestMapping(method = RequestMethod.POST,value = "/forecast_price")
    public ResponseResult<ForcastPriceResult> getPrice(@RequestBody ForecastPriceDTO forecastPriceDTO);
}
