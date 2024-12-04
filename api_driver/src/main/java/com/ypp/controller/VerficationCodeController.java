package com.ypp.controller;

import com.ypp.service.VerfictionCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ypp.dto.ResponseResult;
import ypp.request.VerficationCodeDTO;

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
}
