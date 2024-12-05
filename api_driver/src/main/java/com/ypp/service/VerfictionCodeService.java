package com.ypp.service;

import com.ypp.remote.DriverUserClient;
import com.ypp.remote.VerficationCodeClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import ypp.constant.CommonResponseStatus;
import ypp.constant.DriverCarConstants;
import ypp.constant.IdentityConstant;
import ypp.constant.TokenConstant;
import ypp.dto.ResponseResult;
import ypp.request.VerficationCodeDTO;
import ypp.response.DriverUserExistsResponse;
import ypp.response.NumberCodeResult;
import ypp.response.TokenResponse;
import ypp.utils.JwtUtils;
import ypp.utils.RedisPrefixUtils;

import java.util.concurrent.TimeUnit;


@Service
@Slf4j
public class VerfictionCodeService {
    @Autowired
    DriverUserClient driverUserClient;
    @Autowired
    VerficationCodeClient verficationCodeClient;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    public ResponseResult checkAndSendMessage(String driverPhone){
        //检查手机号是否存在
        ResponseResult<DriverUserExistsResponse> driverUser = driverUserClient.getDriverUser(driverPhone);
        DriverUserExistsResponse data = driverUser.getData();
        Integer isExist = data.getIsExist();
        if(isExist == DriverCarConstants.DRIVER_NOT_EXISTS){
            return ResponseResult.fail(CommonResponseStatus.DRIVER_NOT_EXIST.getCode(),CommonResponseStatus.DRIVER_NOT_EXIST.getValue());
        }
        log.info(driverPhone+"司机存在");
        //发送验证码
        ResponseResult<NumberCodeResult> verficationCode = verficationCodeClient.getVerficationCode(6);
        int code = verficationCode.getData().getNumberCode();
        log.info("验证码为："+code);
        //将验证码存储到redis中
        String key = RedisPrefixUtils.generateKeyByPhone(driverPhone, IdentityConstant.DRIVER_IDENTITY);
        stringRedisTemplate.opsForValue().set(key,code+"",2, TimeUnit.MINUTES);

        return ResponseResult.success("");
    }
    public ResponseResult checkCode(String driverPhone,String verficationCode){
        //根据手机号查询验证码并进行验证
        String key = RedisPrefixUtils.generateKeyByPhone(driverPhone,IdentityConstant.DRIVER_IDENTITY);
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        System.out.println("redis中的value:"+codeRedis);
        //校验验证码非空
        if(StringUtils.isBlank(codeRedis)){
            return ResponseResult.fail(CommonResponseStatus.VERFICARION_CODE_ERROR.getCode(),CommonResponseStatus.VERFICARION_CODE_ERROR.getValue());
        }
        if(!verficationCode.trim().equals(codeRedis.trim())){//用户发的和redis中保存的是否一直

            return ResponseResult.fail(CommonResponseStatus.VERFICARION_CODE_ERROR.getCode(),CommonResponseStatus.VERFICARION_CODE_ERROR.getValue());

        }
        //颁发令牌
        //将token存储到redis中
        String accessToken = JwtUtils.generateToken(driverPhone, IdentityConstant.DRIVER_IDENTITY, TokenConstant.ACCCESS_TOKEN);
        String refreshToken = JwtUtils.generateToken(driverPhone, IdentityConstant.DRIVER_IDENTITY, TokenConstant.REFRESH_TOKEN);

        String accessTokenKey = RedisPrefixUtils.generateTokenKey(driverPhone,IdentityConstant.DRIVER_IDENTITY,TokenConstant.ACCCESS_TOKEN);
        stringRedisTemplate.opsForValue().set(accessTokenKey,accessToken,30,TimeUnit.DAYS);
//        stringRedisTemplate.opsForValue().set(accessTokenKey,accessToken,30,TimeUnit.SECONDS);
        String refreshTokenKey = RedisPrefixUtils.generateTokenKey(driverPhone,IdentityConstant.DRIVER_IDENTITY,TokenConstant.REFRESH_TOKEN);
        stringRedisTemplate.opsForValue().set(refreshTokenKey,refreshToken,31,TimeUnit.DAYS);
//        stringRedisTemplate.opsForValue().set(refreshTokenKey,refreshToken,50,TimeUnit.SECONDS);


        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        return ResponseResult.success(tokenResponse);
    }

}
