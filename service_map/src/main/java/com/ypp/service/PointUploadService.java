package com.ypp.service;

import com.ypp.remote.PointUploadClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ypp.dto.ResponseResult;
import ypp.request.PointRequest;

@Service
public class PointUploadService {
    @Autowired
    PointUploadClient pointUploadClient;
    public ResponseResult uploadPoint(PointRequest pointRequest) {

       return pointUploadClient.uploadPoint(pointRequest);
    }
}
