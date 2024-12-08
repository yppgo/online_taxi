package com.ypp.remote;

import com.ypp.service.TrackControllerService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import ypp.constant.AmapConfigConstant;
import ypp.dto.ResponseResult;
import ypp.response.TrackResponse;

import java.util.ResourceBundle;

@Service

public class TrackControllerClient {
    @Autowired
    RestTemplate restTemplate;
    @Value("${amp.key}")
    private String amapKey;
    @Value("${amp.sid}")
    private String ampSid;
    public ResponseResult<TrackResponse> addTrack(String tid){
        StringBuilder url = new StringBuilder(AmapConfigConstant.ADD_TRACK_URL);

        url.append("?");
        url.append("key="+amapKey);
        url.append("&");
        url.append("sid="+ampSid);
        url.append("&");
        url.append("tid="+tid);
        ResponseEntity<String> forEntity = restTemplate.postForEntity(url.toString(),null, String.class);
        String body = forEntity.getBody();
        JSONObject jsonObject = JSONObject.fromObject(body);
        JSONObject data = jsonObject.getJSONObject("data");
        String trid = data.getString("trid");
        String trname= "";
        if(data.has("trmame")){
            trname = data.getString("trname");
        }
        TrackResponse trackResponse = new TrackResponse();
        trackResponse.setTrid(trid);
        trackResponse.setTrname(trname);
        return ResponseResult.success(trackResponse);
    }
}
