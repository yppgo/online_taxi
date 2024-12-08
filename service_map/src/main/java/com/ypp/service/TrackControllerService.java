package com.ypp.service;

import com.ypp.remote.TrackControllerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ypp.dto.ResponseResult;
import ypp.response.TrackResponse;

@Service

public class TrackControllerService {
    @Autowired
   TrackControllerClient trackControllerClient;

    public ResponseResult<TrackResponse> addTrack(String tid){
       return trackControllerClient.addTrack(tid);
    }
}
