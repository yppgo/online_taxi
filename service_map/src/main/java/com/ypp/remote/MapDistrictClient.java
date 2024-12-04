package com.ypp.remote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ypp.constant.AmapConfigConstant;
import ypp.dto.ResponseResult;

@Service
public class MapDistrictClient {
    @Autowired
    RestTemplate restTemplate;
    @Value("${amp.key}")
    private String amapKey;
    public String district(String keyWord){
        StringBuilder url = new StringBuilder(AmapConfigConstant.DISTRICT_URL);
        url.append("?");
        url.append("keywords="+keyWord);
        url.append("&");
        url.append("subdistrict="+3);
        url.append("&");
        url.append("key="+amapKey);
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url.toString(), String.class);
        //请求数据
        //解析数据
        //插入数据库
        return forEntity.getBody();
    }
}
