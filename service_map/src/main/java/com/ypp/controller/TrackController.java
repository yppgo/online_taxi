package com.ypp.controller;

import com.ypp.service.TrackControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ypp.dto.ResponseResult;
import ypp.response.TrackResponse;

@RestController
@RequestMapping("/track")
public class TrackController {
    @Autowired
    TrackControllerService trackControllerService;
    @PostMapping("/add")
    public ResponseResult<TrackResponse> addTrack(String tid){
        return trackControllerService.addTrack(tid);
    }
}
