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
public class TerminalClient {
    @Autowired
    RestTemplate restTemplate;
    @Value("${amp.key}")
    private String amapKey;
    @Value("${amp.sid}")
    private String ampSid;
    public ResponseResult addTerminal(String name,String desc){
        StringBuilder url = new StringBuilder(AmapConfigConstant.ADD_TERMINAL_URL);

        url.append("?");
        url.append("key="+amapKey);
        url.append("&");
        url.append("sid="+ampSid);
        url.append("&");
        url.append("name="+name);
        url.append("&");
        url.append("desc="+desc);
        ResponseEntity<String> forEntity = restTemplate.postForEntity(url.toString(),null, String.class);
        String body = forEntity.getBody();
        JSONObject jsonObject = JSONObject.fromObject(body);
        JSONObject data = jsonObject.getJSONObject("data");
        String tid = data.getString("tid");
        return ResponseResult.success(tid);
    }
    public ResponseResult aroundSearch(String center,Integer radius){
        StringBuilder url = new StringBuilder(AmapConfigConstant.AROUND_SEARCH_URL);

        url.append("?");
        url.append("key="+amapKey);
        url.append("&");
        url.append("sid="+ampSid);
        url.append("&");
        url.append("center="+center);
        url.append("&");
        url.append("radius="+radius);
        System.out.println("终端搜索："+url.toString());
        ResponseEntity<String> forEntity = restTemplate.postForEntity(url.toString(),null, String.class);
        System.out.println("搜索后的结果为："+forEntity.getBody());
        return null;
    }
}
