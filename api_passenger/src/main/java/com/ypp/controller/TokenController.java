package com.ypp.controller;

import com.ypp.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ypp.dto.ResponseResult;
import ypp.response.TokenResponse;

import javax.websocket.server.PathParam;

@RestController
public class TokenController {
    @Autowired
    private TokenService tokenService;
    @PostMapping("/refresh_token")
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse){
        String src = tokenResponse.getRefreshToken();

        return tokenService.refreshToken(src);
    }
}
