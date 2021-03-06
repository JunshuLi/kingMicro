package com.king.common.utils.redis;

/**
 *  Redis所有Keys
 * @author King chen
 * @date 2017年12月25日
 */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }

    public static String getShiroSessionKey(String key){
        return "sessionid:" + key;
    }
    
    public static String getTokenKey(String key){
        return "token:" + key;
    }
    
    public static String getEnttyKey(String key){
        return "entty:" + key;
    }
    
    public static String getKaptchaKey(String key){
        return "kaptcha:" + key;
    }
    
    public static String getSerialNoKey(String key){
        return "serialNo:" + key;
    }
    
    public static String getSysDicKey(String key){
        return "sys:dic:" + key;
    }
    
    /**
     * 根据用户id获取权限key
     * @param userId
     * @param key
     * @return
     */
    public static String getPermsKey(Object userId,String key){
        return "perms:"+userId+":" + key;
    }
    
    public static String getErrorIPKey(String ip,String key){
        return "login:"+ key+ip;
    }
    
    public static String getLoginKey(String key){
        return "login:"+ key;
    }
}
