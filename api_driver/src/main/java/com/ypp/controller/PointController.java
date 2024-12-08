package com.ypp.controller;

import com.ypp.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ypp.dto.ResponseResult;
import ypp.request.ApiDriverPointRequest;

@RestController
@RequestMapping("/point")
public class PointController {
    @Autowired
    PointService pointService;
    @PostMapping("/upload")
    public ResponseResult pointUpload(@RequestBody ApiDriverPointRequest apiDriverPointRequest){


        return pointService.pointUpload(apiDriverPointRequest);
    }
}
