package com.ypp.service;

import com.ypp.mapper.PriceRuleMapper;
import com.ypp.remote.ServiceMapClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ypp.constant.CommonResponseStatus;
import ypp.dto.PriceRule;
import ypp.dto.ResponseResult;
import ypp.request.ForecastPriceDTO;
import ypp.response.DirectionResponse;
import ypp.response.ForcastPriceResult;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ForecastPriceService {
    @Autowired
    public ServiceMapClient serviceMapClient;
    @Autowired
    private PriceRuleMapper priceRuleMapper;
    public ResponseResult forecastPrice(String depLongitude,String depLatitude,String desLongitude,String desLatitude){
        log.info("起点经度为："+ depLongitude);
        log.info("起点纬度为："+ depLatitude);
        log.info("终点经度为："+ desLongitude);
        log.info("终点经度为："+ desLatitude);
        log.info("调用地图服务查询距离和时常");
        log.info("读取计价规则");
        log.info("计算价格");
        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDesLongitude(desLongitude);
        forecastPriceDTO.setDesLatitude(desLatitude);
        ResponseResult<DirectionResponse> direction = serviceMapClient.direction(forecastPriceDTO);
        Integer distance = direction.getData().getDistance();
        Integer duration = direction.getData().getDuration();
        log.info("距离："+ direction.getData().getDistance()+"时间：" + direction.getData().getDuration());
        log.info("读取计价规则");

        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("city_code","110000");
        queryMap.put("vehicle_type","1");
        List<PriceRule> priceRules = priceRuleMapper.selectByMap(queryMap);
        if(priceRules.size() == 0){
            return ResponseResult.fail(CommonResponseStatus.USER_NOT_EXIST.getCode(),CommonResponseStatus.USER_NOT_EXIST.getValue());
        }
        PriceRule priceRule = priceRules.get(0);
        log.info("计算价格");
        double price = getPrice(distance,duration, priceRule);
        ForcastPriceResult forcastPriceResult = new ForcastPriceResult();
        forcastPriceResult.setPrice(price);
        return ResponseResult.success(forcastPriceResult);
    }
    private  double getPrice(Integer distance,Integer duration,PriceRule priceRule){

        BigDecimal price = new BigDecimal(0);
        //起步价
        Double startFare = priceRule.getStartFare();

        BigDecimal startFareDecimal = new BigDecimal(startFare);
        price = price.add(startFareDecimal);
        //里程费
        BigDecimal distanceDecimal = new BigDecimal(distance);
        BigDecimal distanceMileDecimal = distanceDecimal.divide(new BigDecimal(1000),2,BigDecimal.ROUND_HALF_UP);

        // 起步里程
        Integer startMile = priceRule.getStartMile();
        BigDecimal startMileDecimal = new BigDecimal(startMile);
        double distanceSubtract  = distanceMileDecimal.subtract(startMileDecimal).doubleValue();

        //最终收费里程
        Double mile = distanceSubtract < 0 ? 0 : distanceSubtract;
        BigDecimal mileDecimal = new BigDecimal(mile);
        Double unitPerMile = priceRule.getUnitPricePerMile();
        BigDecimal unitPerMileDecimal = new BigDecimal(unitPerMile);

        BigDecimal mileFare = mileDecimal.multiply(unitPerMileDecimal).setScale(2,BigDecimal.ROUND_HALF_UP);
        price = price.add(mileFare);

        //时常费
        BigDecimal time = new BigDecimal(duration);
        BigDecimal timeDecimal = time.divide(new BigDecimal(60),2, BigDecimal.ROUND_HALF_UP);
        Double unitPerMinute = priceRule.getUnitPricePerMinute();
        BigDecimal unitPerMinuteDecimal = new BigDecimal(unitPerMinute);
        //时常费用
        BigDecimal timeFare = timeDecimal.multiply(unitPerMinuteDecimal);
        price = price.add(timeFare);

        return price.doubleValue();
    }

   /* public static void main(String[] args) {
        PriceRule priceRule = new PriceRule();
        priceRule.setUnitPricePerMile(1.8);
        priceRule.setUnitPricePerMinute(0.5);
        priceRule.setStartFare(10.0);
        priceRule.setStartMile(3);
        System.out.println(getPrice(6500,1800,priceRule));
    }*/
}
