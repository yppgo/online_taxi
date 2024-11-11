package com.ypp.service;

import com.ypp.remote.ServiceVerficationcodeClient;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import ypp.dto.ResponseResult;
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
    public ResponseResult checkCode(String passengerPhone,String verficationCode){
        System.out.println("根据手机号去读取验证码");
        System.out.println("校验验证码");
        System.out.println("判断原来是否有用户并进行对应处理");
        System.out.println("颁发令牌");
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken("token_value");
        return ResponseResult.success(tokenResponse);
    }

}
