package com.ypp.remote;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.tags.EscapeBodyTag;
import ypp.constant.AmapConfigConstant;
import ypp.response.DirectionResponse;

@Service
@Slf4j
public class MapDirectionClient {
    @Value("${amp.key}")
    private String ampKey;
    @Autowired
    RestTemplate restTemplate;
    public DirectionResponse direction(String depLongitude, String depLatitude, String desLongitude, String desLatitude){
        //组装url
        //调用高德api  %3C%E7%94%A8%E6%88%B7%E7%9A%84key%3E
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(AmapConfigConstant.DIRECTION_URL);
        urlBuilder.append("?origin="+depLongitude+","+depLatitude+"&destination="+desLongitude+","+desLatitude+"&extensions=base&output=json&key="+ampKey);
        log.info(urlBuilder.toString());
        ResponseEntity<String> forEntity = restTemplate.getForEntity(urlBuilder.toString(), String.class);
        String directionString = forEntity.getBody();
        DirectionResponse directionResponse = parseDirectionEntity(directionString);
        return directionResponse;
    }
    //解析高德传输过来的数据
    private DirectionResponse parseDirectionEntity(String directionString){
        DirectionResponse directionResponse = null;
        try{
            directionResponse = new DirectionResponse();
            JSONObject result = JSONObject.fromObject(directionString);
            if(result.has(AmapConfigConstant.STATUS)){
                int status = result.getInt(AmapConfigConstant.STATUS);
                if(status == 1){
                    if(result.has(AmapConfigConstant.ROUTE)){
                        JSONObject routeObject = result.getJSONObject(AmapConfigConstant.ROUTE);
                        JSONArray pathsArray = routeObject.getJSONArray(AmapConfigConstant.PATH);
                        JSONObject pathObject = pathsArray.getJSONObject(0);
                        if(pathObject.has(AmapConfigConstant.DISTANCE)){
                            int distance = pathObject.getInt(AmapConfigConstant.DISTANCE);
                            directionResponse.setDistance(distance);
                        }
                        if(pathObject.has(AmapConfigConstant.DURATION)){
                            int duration = pathObject.getInt(AmapConfigConstant.DURATION);
                            directionResponse.setDuration(duration);
                        }
                    }
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return directionResponse;
    }
}
