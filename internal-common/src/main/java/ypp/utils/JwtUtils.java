package ypp.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import ypp.constant.IdentityConstant;
import ypp.constant.TokenConstant;
import ypp.dto.TokenResult;
import ypp.response.TokenResponse;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    //构成token的盐
    private static final String SIGN="efasdfasd";
    private static final String JWT_KEY_phone = "phone";
    private static final String JWT_KEY_identity = "identity";
    private static final String JWT_TOKEN_TYPE = "tokenType";
    private static final String JWT_TOKEN_TIME = "tokenTime";
    //生成token
    public static String generateToken(String passengerPhone, String identity,String tokenType){
        Map<String,String> map = new HashMap<>();
        map.put(JWT_KEY_phone,passengerPhone);
        map.put(JWT_KEY_identity,identity);
        map. put(JWT_TOKEN_TYPE,tokenType);
        map.put(JWT_TOKEN_TIME,Calendar.getInstance().getTime().toString());
        JWTCreator.Builder builder = JWT.create();
        map.forEach(
                (k,v)->{
                    builder.withClaim(k,v);
                }
        );
        //builder.withExpiresAt(date);
        String sign = builder.sign(Algorithm.HMAC256(SIGN));

        return sign;
    }
    //解析token
    public static TokenResult parseToken(String token){
        DecodedJWT verfiy = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        String passengerPhone = verfiy.getClaim(JWT_KEY_phone).asString();
        String identity = verfiy.getClaim(JWT_KEY_identity).asString();
        TokenResult tokenResult = new TokenResult();
        tokenResult.setIdentity(identity);
        tokenResult.setPhone(passengerPhone);
        return tokenResult;
    }

    public static TokenResult checkToken(String token){
        TokenResult tokenResult = null;
        try{
            tokenResult = JwtUtils.parseToken(token);
        }catch (Exception e){
            e.printStackTrace();
        }
        return tokenResult;
    }
    public static void main(String[] args) {
        String s= generateToken("17768483219", IdentityConstant.PASSENGER_IDENTITY, TokenConstant.ACCCESS_TOKEN);
        System.out.println("生成的token:"+s);
        TokenResult res = parseToken(s);
        System.out.println("解析token后的值："+res.getIdentity()+res.getPhone());

    }
}
