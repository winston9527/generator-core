package com.winston.code.generator.core.util;

/**
 * @author Winston
 * @version 2017/3/21- 17:58
 */
public class StringUtil {

    /**
     * @return
     */
    public static String capFirst(String str) {
        char[] cs = str.toCharArray();
        if (cs[0] < 123 && cs[0] > 91) { //判断是否为小写字母
            cs[0] -= 32;
        }
        return String.valueOf(cs);
    }

    /**
     * 转换分隔符为驼峰命名法
     *
     * @param str
     * @return
     */
    public static String coverSeparatorToHump(String str) {
        str = str.toLowerCase();
        if (str.contains("_")) {

            String[] columnNameArray = str.split("_");
            StringBuffer sbfStr = new StringBuffer(columnNameArray[0]);
            for (int i = 1; i < columnNameArray.length; i++) {
                if (!"".equals(columnNameArray[i])) {
                    sbfStr.append(StringUtil.capFirst(columnNameArray[i]));
                }
            }
            return sbfStr.toString();
        }
        return str;
    }

    /**
     * 判断是否为空
     *
     * @param value 判断的值
     * @return
     */
    public static boolean isEmpty(String value) {
        if (value == null || "".equals(value.trim())) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(capFirst("a"));
    }
}
