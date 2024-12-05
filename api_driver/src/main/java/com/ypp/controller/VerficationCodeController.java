package com.ypp.controller;

import com.ypp.service.VerfictionCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ypp.dto.ResponseResult;
import ypp.request.VerficationCodeDTO;
import ypp.response.DriverUserExistsResponse;
import ypp.response.NumberCodeResult;

@RestController
@Slf4j
public class VerficationCodeController {
    @Autowired
    VerfictionCodeService verfictionCodeService;

    @GetMapping("/verification_code")
    public ResponseResult verficationCode(@RequestBody VerficationCodeDTO verficationCodeDTO){

        String driverPhone = verficationCodeDTO.getDriverPhone();
        log.info("司机号码为："+driverPhone);
        return verfictionCodeService.checkAndSendMessage(driverPhone);
    }
    @GetMapping("/check_drive/{driverPhone}")
    public ResponseResult<DriverUserExistsResponse> getDriverUser(@PathVariable("driverPhone") String driverPhone){

       return verfictionCodeService.checkAndSendMessage(driverPhone);
    }
    @PostMapping("/verfication_code_check")
    public ResponseResult checkVerfication(@RequestBody VerficationCodeDTO verficationCodeDTO){
        //获取请求体中的电话号码和验证码
        String driverPhone = verficationCodeDTO.getDriverPhone();
        String verficationCode = verficationCodeDTO.getVerficationCode();
        //校验
        System.out.println("获取到的手机号码为："+driverPhone);
        System.out.println("获取到的验证码为："+verficationCode);
        //没有创建
        //有就返回
        return verfictionCodeService.checkCode(driverPhone,verficationCode);
    }
}
