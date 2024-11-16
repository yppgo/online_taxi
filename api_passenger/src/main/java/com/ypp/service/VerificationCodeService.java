package com.ypp.service;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.ypp.remote.ServicePassengerUserClient;
import com.ypp.remote.ServiceVerficationcodeClient;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import ypp.constant.CommonResponseStatus;
import ypp.dto.ResponseResult;
import ypp.request.VerficationCodeDTO;
import ypp.response.NumberCodeResult;
import ypp.response.TokenResponse;

import java.util.concurrent.TimeUnit;

@Service
public class VerificationCodeService {
    //定义redis中保存数据的前缀
    private String verfication_code_prefix = "passenger_verfication_code_";
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ServiceVerficationcodeClient serviceVerficationcodeClient;
    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;
    /**
     * 生成验证码
     */

    public ResponseResult generateCode(String passengerPhone){
        //1.通过调用服务获取验证码,获得验证码
        ResponseResult<NumberCodeResult> numberCode = serviceVerficationcodeClient.getNumberCode(5);
        int numberCode1 = numberCode.getData().getNumberCode();
        System.out.println("远程调用获取的验证码为："+numberCode1);
        //2将验证码存储到redis中
        //设置key
        String key = verfication_code_prefix+passengerPhone;
        try {
            stringRedisTemplate.opsForValue().set(key, numberCode1+"", 2, TimeUnit.MINUTES);
        } catch (Exception e) {
            // 处理可能的异常
            e.printStackTrace();
        }
        //3通过第三方服务发送短信验证码到手机中
        return ResponseResult.success();
    }
    public String generateKeyByPhone(String passengerPhone){
        String key = verfication_code_prefix+passengerPhone;
        return key;
    }
    public ResponseResult checkCode(String passengerPhone,String verficationCode){
        //根据手机号查询验证码并进行验证
        String key = generateKeyByPhone(passengerPhone);
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        System.out.println("redis中的value:"+codeRedis);
        //校验验证码非空
        if(StringUtils.isBlank(codeRedis)){
            return ResponseResult.fail(CommonResponseStatus.VERFICARION_CODE_ERROR.getCode(),CommonResponseStatus.VERFICARION_CODE_ERROR.getValue());
        }
        if(!verficationCode.trim().equals(codeRedis.trim())){//用户发的和redis中保存的是否一直

            return ResponseResult.fail(CommonResponseStatus.VERFICARION_CODE_ERROR.getCode(),CommonResponseStatus.VERFICARION_CODE_ERROR.getValue());

        }
        //判断数据库中是否有该用户，没有就创建该用户
        VerficationCodeDTO verficationCodeDTO = new VerficationCodeDTO();
        verficationCodeDTO.setPassengerPhone(passengerPhone);
        servicePassengerUserClient.loginOrRegister(verficationCodeDTO);
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken("token_value");
        return ResponseResult.success(tokenResponse);
    }

}
