package com.ypp.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import ypp.constant.CommonResponseStatus;
import ypp.constant.TokenConstant;
import ypp.dto.ResponseResult;
import ypp.dto.TokenResult;
import ypp.response.TokenResponse;
import ypp.utils.JwtUtils;
import ypp.utils.RedisPrefixUtils;

import java.util.concurrent.TimeUnit;

@Service
public class TokenService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    public ResponseResult refreshToken(String refreshTokenSrc){
        //解析token
        TokenResult tokenResult = JwtUtils.checkToken(refreshTokenSrc);
        //读取redis中token
        if(tokenResult == null){
            return ResponseResult.fail(CommonResponseStatus.TOKEN_ERROR.getCode(),CommonResponseStatus.TOKEN_ERROR.getValue());
        }
        String phone = tokenResult.getPhone();
        String identity = tokenResult.getIdentity();
        //1.生成tokenKey
        String refreshTokenKey = RedisPrefixUtils.generateTokenKey(phone, identity, TokenConstant.REFRESH_TOKEN);
        //校验
        String refreshTokenRedis = redisTemplate.opsForValue().get(refreshTokenKey);
        if((StringUtils.isBlank(refreshTokenRedis)) || !refreshTokenSrc.trim().equals(refreshTokenRedis.trim())){
            return ResponseResult.fail(CommonResponseStatus.TOKEN_ERROR.getCode(),CommonResponseStatus.TOKEN_ERROR.getValue());
        }
        //生成双token
        //生成key
        String acdessTokenKey = RedisPrefixUtils.generateTokenKey(phone, identity, TokenConstant.ACCCESS_TOKEN);
        //生成value
        String accessToken = JwtUtils.generateToken(phone, identity, TokenConstant.ACCCESS_TOKEN);
        String refreshToken = JwtUtils.generateToken(phone, identity, TokenConstant.REFRESH_TOKEN);

        redisTemplate.opsForValue().set(refreshTokenKey,refreshToken,31, TimeUnit.DAYS);
        redisTemplate.opsForValue().set(acdessTokenKey,accessToken,30, TimeUnit.DAYS);
//        redisTemplate.opsForValue().set(refreshTokenKey,refreshToken,50, TimeUnit.SECONDS);
//        redisTemplate.opsForValue().set(acdessTokenKey,accessToken,30, TimeUnit.SECONDS);
        //返回token
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        return ResponseResult.success(tokenResponse);
    }
}
