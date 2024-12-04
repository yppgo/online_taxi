package com.ypp.controller;

import com.ypp.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ypp.dto.District;
import ypp.dto.ResponseResult;

@RestController
public class DistrictController {
    @Autowired
    DistrictService districtService;
    @GetMapping("/district")
    public ResponseResult InitDistrict(String keyword){

        return districtService.initDistrict(keyword);
    }
}
