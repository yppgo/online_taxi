package com.ypp.controller;

import com.ypp.remote.PointUploadClient;
import com.ypp.service.PointUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ypp.dto.ResponseResult;
import ypp.request.PointRequest;

@RestController
@RequestMapping("/point")
public class PointController {
    @Autowired
    PointUploadService pointUploadService;
    @PostMapping("/upload")
    public ResponseResult upLoadPoint(@RequestBody PointRequest pointRequest){
       return pointUploadService.uploadPoint(pointRequest);
    }
}
