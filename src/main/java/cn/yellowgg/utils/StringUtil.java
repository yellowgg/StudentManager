package cn.yellowgg.utils;

/**
 * @Author:黄广
 * @Description:字符串工具类
 * @Date: Created in 19-3-18 下午4:48
 */
public class StringUtil {

    public static boolean isEmpty(String str) {
        if ("".equals(str) || str == null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotEmpty(String str) {
        if (!"".equals(str) && str != null) {
            return true;
        } else {
            return false;
        }
    }
}
