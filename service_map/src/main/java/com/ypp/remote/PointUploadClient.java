package com.ypp.remote;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ypp.constant.AmapConfigConstant;
import ypp.dto.ResponseResult;
import ypp.request.PointDTO;
import ypp.request.PointRequest;

import java.net.URI;

@Service
public class PointUploadClient {
    @Autowired
    RestTemplate restTemplate;
    @Value("${amp.key}")
    private String amapKey;
    @Value("${amp.sid}")
    private String ampSid;
    public ResponseResult uploadPoint(PointRequest pointRequest){
        StringBuilder url = new StringBuilder(AmapConfigConstant.POINT_UPLOAD_URL);

        url.append("?");
        url.append("key="+amapKey);
        url.append("&");
        url.append("sid="+ampSid);
        url.append("&");
        url.append("tid="+pointRequest.getTid());
        url.append("&");
        url.append("trid="+pointRequest.getTrid());
        url.append("&");
        url.append("points=");
        PointDTO[] points = pointRequest.getPoints();
        url.append("%5B");
        for (PointDTO pointDTO:points) {
            url.append("%7B");
            String locatetime = pointDTO.getLocatetime();
            String location = pointDTO.getLocation();
            url.append("%22location%22");
            url.append("%3A");
            url.append("%22"+location+"%22");
            url.append(",");
            url.append("%22locatetime%22");
            url.append("%3A");
            url.append("%22"+locatetime+"%22");
            url.append("%7D");
        }
        url.append("%5D");
        System.out.println("请求的url为："+url);
        ResponseEntity<String> forEntity = restTemplate.postForEntity(URI.create(url.toString()),null, String.class);
        System.out.println("响应的内容为："+forEntity.getBody());
        String body = forEntity.getBody();
        System.out.println(body);
        return ResponseResult.success("");
    }
}
