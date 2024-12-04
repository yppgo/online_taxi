package com.ypp.service;

import org.springframework.stereotype.Service;
import ypp.dto.ResponseResult;
import ypp.request.VerficationCodeDTO;


@Service
public class VerfictionCodeService {
    public ResponseResult checkAndSendMessage(String driverPhone){
        //检查手机号是否存在
        //发送验证码
        //将验证码存储到redis中
        return ResponseResult.success("");
    }
}
