package com.ypp.remote;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ypp.constant.AmapConfigConstant;
import ypp.dto.ResponseResult;

@Service
public class ServiceClient {
    @Autowired
    RestTemplate restTemplate;
    @Value("${amp.key}")
    private String amapKey;
    public ResponseResult addService(String name){
        StringBuilder url = new StringBuilder(AmapConfigConstant.ADD_SERVICE_URL);
        url.append("?");
        url.append("key="+amapKey);
        url.append("&");
        url.append("name="+name);
        ResponseEntity<String> forEntity = restTemplate.postForEntity(url.toString(),null, String.class);
        String body = forEntity.getBody();
        JSONObject jsonObject = JSONObject.fromObject(body);
        JSONObject data = jsonObject.getJSONObject("data");
        String sid = data.getString("sid");
        return ResponseResult.success(sid);
    }
}
