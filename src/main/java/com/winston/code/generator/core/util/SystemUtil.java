package com.winston.code.generator.core.util;

import java.net.InetAddress;

/**
 * @author Winston
 * @version 2017/4/5- 10:24
 */
public class SystemUtil {

    public static String getHostName() {
        String hostName ="";
        InetAddress addr;
        try {
            addr = InetAddress.getLocalHost();//新建一个InetAddress类
            hostName = addr.getHostName().toString();// 获得本机名称
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hostName;
    }
}
