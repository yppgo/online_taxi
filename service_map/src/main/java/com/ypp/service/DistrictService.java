package com.ypp.service;

import com.ypp.mapper.DistrictMapper;
import com.ypp.remote.MapDistrictClient;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ypp.constant.AmapConfigConstant;
import ypp.constant.CommonResponseStatus;
import ypp.dto.District;
import ypp.dto.ResponseResult;

@Service
@Slf4j
public class DistrictService {
    @Autowired
    private MapDistrictClient mapDistrictClient;
    @Autowired
    DistrictMapper districtMapper;
    public ResponseResult initDistrict(String keyWord){
        //拼装url
        //&2&key=<用户的key>
        //请求数据
        String district = mapDistrictClient.district(keyWord);
        log.info(district);
        JSONObject districtObject = JSONObject.fromObject(district);
        int status = districtObject.getInt(AmapConfigConstant.STATUS);
        if(status != 1){
            return ResponseResult.fail(CommonResponseStatus.MAP_DISTRIT_ERROR.getCode(),CommonResponseStatus.MAP_DISTRIT_ERROR.getValue());
        }
        JSONArray districtsJsonArray = districtObject.getJSONArray(AmapConfigConstant.DISTRICTS);
        for(int i = 0; i < districtsJsonArray.size(); i++){
            JSONObject countryJsonObject = districtsJsonArray.getJSONObject(i);
            String countryAddressCode = countryJsonObject.getString(AmapConfigConstant.ADCODE);
            String countryLevel = countryJsonObject.getString(AmapConfigConstant.LEVEL);
            String countryName = countryJsonObject.getString(AmapConfigConstant.NAME);
            String countrtParentAddressCode = "0";

            insertDistrict(countryAddressCode,countryName,countryLevel,countrtParentAddressCode);
            //插入第一层数据
            JSONArray provinceJsonArray = countryJsonObject.getJSONArray(AmapConfigConstant.DISTRICTS);
            for(int j = 0; j < provinceJsonArray.size(); j++){
                JSONObject provinceJsonObject = provinceJsonArray.getJSONObject(j);
                String provinceAddressCode = provinceJsonObject.getString(AmapConfigConstant.ADCODE);
                String provinceName = provinceJsonObject.getString(AmapConfigConstant.NAME);
                String provinceLevel = provinceJsonObject.getString(AmapConfigConstant.LEVEL);
                String provinceParentAddressCode = "1";
                insertDistrict(provinceAddressCode,provinceName,provinceLevel,provinceParentAddressCode);
                JSONArray cityJsonArray = provinceJsonObject.getJSONArray(AmapConfigConstant.DISTRICTS);
                for(int k = 0; k < cityJsonArray.size(); k++){
                    JSONObject cityJsonObject = cityJsonArray.getJSONObject(k);
                    String cityAddressCode = cityJsonObject.getString(AmapConfigConstant.ADCODE);
                    String cityName = cityJsonObject.getString(AmapConfigConstant.NAME);
                    String cityLevel = cityJsonObject.getString(AmapConfigConstant.LEVEL);
                    String cityParentAddressCode = "2";
                    insertDistrict(cityAddressCode,cityName,cityLevel,cityParentAddressCode);
                    JSONArray districtJsonArray1 = cityJsonObject.getJSONArray(AmapConfigConstant.DISTRICTS);
                    for(int w = 0; w < districtJsonArray1.size(); w++){
                        JSONObject districtJsonObject1 = districtJsonArray1.getJSONObject(w);
                        String districtAddressCode1 = districtJsonObject1.getString(AmapConfigConstant.ADCODE);
                        String districtName1 = districtJsonObject1.getString(AmapConfigConstant.NAME);
                        String districtLevel1 = districtJsonObject1.getString(AmapConfigConstant.LEVEL);
                        String districtParentAddressCode1 = "3";
                        if(districtLevel1.equals(AmapConfigConstant.STREET)){
                            continue;
                        }
                        insertDistrict(districtAddressCode1,districtName1,districtLevel1,districtParentAddressCode1);
                    }

                }
            }
            //插入第二层数据
            //插入第三层数据
            //插入最后一层数据
        }
        //解析数据
        //插入数据库
        return null;
    }
    public int generateLevel(String level){
        int levelInt = 0;
        if(level.trim().equals("country")){
            levelInt = 0;
        }else if(level.trim().equals("province")){
            levelInt =  1;
        }else if(level.trim().equals("city")){
            levelInt =  2;
        }else if(level.trim().equals("district")){
            levelInt = 3;
        }
        return levelInt;
    }

    public void insertDistrict(String addressCode,String name,String level,String parentAddredssCode){
        District district1= new District();
        district1.setAddressCode(addressCode);
        district1.setAddressName(name);
        int levelInt = generateLevel(level);

        district1.setLevel(levelInt);
        district1.setParentAddressCode(parentAddredssCode);
        districtMapper.insert(district1);
    }
}
