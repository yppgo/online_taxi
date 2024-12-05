package ypp.utils;

public class RedisPrefixUtils {


    public  static String verfication_code_prefix = "verfication_code_";
    public static String tokenPrefix = "token_";
    public static String generateKeyByPhone(String phone,String identity){
        String key = verfication_code_prefix+"_"+identity+"_"+phone;
        return key;
    }
    public  static String generateTokenKey(String phone,String identity,String tokenTpye){
        return tokenPrefix+phone+"_"+identity+"_"+tokenTpye;
    }
}
