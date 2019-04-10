package cn.yellowgg.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author:黄广
 * @Description: 日期转换
 * @Date: Created in 19-3-22 上午10:51
 */
public class DateUtil {

    public static String formatDate(Date date, String format) {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (date != null) {
            result = sdf.format(date);
        }
        return result;
    }


    public static Date formatString(String str, String format) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(str);
    }
}
