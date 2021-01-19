package com.winston.code.generator.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Description:
 * @Author Winston
 * @Version 1.0 2017/4/20 8:55
 */
public class ConfigUtil {

    private static Properties confProperties;

    public static String getConfigByKey(String key){
        String result =null;
        if(confProperties==null){
            confProperties = new Properties();
            InputStream in = ConfigUtil.class.getClassLoader().getResourceAsStream("conf/conf.properties");
            try {
                confProperties.load(in);     ///加载属性列表
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Object value = confProperties.get(key);
        if(value!=null){
            result = value.toString();
        }
        return result;
    }


    /**
     * dbUrl
     */
    public static final String DBURL = "dbUrl";
    /**
     * user
     */
    public static final String USERNAME = "userName";
    /**
     * password
     */
    public static final String PASSWORD = "password";

}
