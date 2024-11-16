package com.ypp.controller;

import com.ypp.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ypp.dto.ResponseResult;
import ypp.request.VerficationCodeDTO;

@RestController
public class VerficationCodeController {
    @Autowired
    private VerificationCodeService verificationCodeService;
    @GetMapping("/verification_code")
    public ResponseResult verficationCode(@RequestBody VerficationCodeDTO verficationCodeDTO){
        //1.接受数据
        String passengerPhone = verficationCodeDTO.getPassengerPhone();
        System.out.println(passengerPhone);
        //2.调用生成验证码服务并保存

        return verificationCodeService.generateCode(passengerPhone) ;
    }
    @PostMapping("/verfication_code_check")
    public ResponseResult checkVerfication(@RequestBody VerficationCodeDTO verficationCodeDTO){
        //获取请求体中的电话号码和验证码
        String passengerPhone = verficationCodeDTO.getPassengerPhone();
        String verficationCode = verficationCodeDTO.getVerficationCode();
        //校验
        System.out.println("获取到的手机号码为："+passengerPhone);
        System.out.println("获取到的验证码为："+verficationCode);
        //没有创建
        //有就返回
        return verificationCodeService.checkCode(passengerPhone,verficationCode);
    }
}
