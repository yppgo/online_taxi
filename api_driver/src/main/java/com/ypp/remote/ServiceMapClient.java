package com.ypp.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ypp.dto.ResponseResult;
import ypp.request.PointRequest;
import ypp.response.TrackResponse;

import java.awt.*;

@FeignClient("service-map")
public interface ServiceMapClient {

    @RequestMapping(method = RequestMethod.POST,value = "/point/upload")
    public ResponseResult pointUpload(@RequestBody PointRequest pointRequest);
}
