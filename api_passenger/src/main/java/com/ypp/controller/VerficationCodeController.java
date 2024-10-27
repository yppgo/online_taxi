package com.ypp.controller;

import com.ypp.request.VerficationCodeDTO;
import com.ypp.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerficationCodeController {
    @Autowired
    private VerificationCodeService verificationCodeService;
    @GetMapping("/verification_code")
    public String verficationCode(@RequestBody VerficationCodeDTO verficationCodeDTO){
        //1.接受数据
        String passengerPhone = verficationCodeDTO.getPassengerPhone();
        System.out.println(passengerPhone);
        //2.调用生成验证码服务并保存

        return verificationCodeService.generateCode(passengerPhone) ;
    }
}
