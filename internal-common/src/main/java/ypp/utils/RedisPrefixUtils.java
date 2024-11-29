package ypp.utils;

public class RedisPrefixUtils {


    public  static String verfication_code_prefix = "passenger_verfication_code_";
    public static String tokenPrefix = "token_";
    public static String generateKeyByPhone(String passengerPhone){
        String key = verfication_code_prefix+passengerPhone;
        return key;
    }
    public  static String generateTokenKey(String phone,String identity,String tokenTpye){
        return tokenPrefix+phone+"_"+identity+"-"+tokenTpye;
    }
}
