package com.fengzkframework.basic.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {
    public static String getDateTimeStr(Date currentTime) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return  dateString;
    }
}
