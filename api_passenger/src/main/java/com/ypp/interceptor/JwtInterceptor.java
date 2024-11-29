package com.ypp.interceptor;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import ypp.constant.TokenConstant;
import ypp.dto.ResponseResult;
import ypp.dto.TokenResult;
import ypp.utils.JwtUtils;
import ypp.utils.RedisPrefixUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
//拦截请求验证token
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = true;
        String resultString = "";

        String token = request.getHeader("Authorization");
       //////这边刚刚删除了一部分
        TokenResult tokenResult = JwtUtils.checkToken(token);
        //从redis中取出token
        if(tokenResult == null){
            resultString = "token invalid";
            result = false;
        }else{
            String phone = tokenResult.getPhone();
            String identity = tokenResult.getIdentity();
            String tokenKey = RedisPrefixUtils.generateTokenKey(phone,identity, TokenConstant.ACCCESS_TOKEN);
            String tokenRedis = stringRedisTemplate.opsForValue().get(tokenKey);
            if((StringUtils.isBlank(tokenRedis)) || !token.trim().equals(tokenRedis.trim())){
                resultString = "token invalid";
                result = false;
            }
        }
        //比较
        if(!result){
            PrintWriter out = response.getWriter();
            out.print(JSONObject.fromObject(ResponseResult.fail(resultString)));
        }

        return true;
    }
}
