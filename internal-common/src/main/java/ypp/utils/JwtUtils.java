package ypp.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import ypp.constant.IdentityConstant;
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

    //生成token
    public static String generateToken(String passengerPhone, String identity){
        Map<String,String> map = new HashMap<>();
        map.put(JWT_KEY_phone,passengerPhone);
        map.put(JWT_KEY_identity,identity);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,1);
        Date date =calendar.getTime();
        JWTCreator.Builder builder = JWT.create();
        map.forEach(
                (k,v)->{
                    builder.withClaim(k,v);
                }
        );
        builder.withExpiresAt(date);
        String sign = builder.sign(Algorithm.HMAC256(SIGN));

        return sign;
    }
    //解析token
    public static TokenResult parseToken(String token){
        DecodedJWT verfiy = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        String passengerPhone = verfiy.getClaim(JWT_KEY_phone).toString();
        String identity = verfiy.getClaim(JWT_KEY_identity).toString();
        TokenResult tokenResult = new TokenResult();
        tokenResult.setIdentity(identity);
        tokenResult.setPhone(passengerPhone);
        return tokenResult;
    }
    public static void main(String[] args) {
        String s= generateToken("17768483219", IdentityConstant.PASSENGER_IDENTITY);
        System.out.println("生成的token:"+s);
        TokenResult res = parseToken(s);
        System.out.println("解析token后的值："+res.getIdentity()+res.getPhone());

    }

}
