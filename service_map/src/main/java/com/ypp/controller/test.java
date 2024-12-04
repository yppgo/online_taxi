package com.ypp.controller;

import com.ypp.mapper.DistrictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ypp.dto.District;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class test {
    @Autowired
    DistrictMapper districtMapper;
    @GetMapping("/test")
    public String test(){
        return "test";
    }
    @GetMapping("/test-map")
    public String testMapper(){
        Map<String,Object> map = new HashMap<>();
        map.put("address_code","110000");
        List<District> districts = districtMapper.selectByMap(map);
        if(districts.size() != 0) {
            districts.get(0);
        }
        return "yes";
    }
}
