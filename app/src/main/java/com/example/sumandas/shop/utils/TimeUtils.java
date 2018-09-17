package com.example.sumandas.shop.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {


    public static final int TIMEOUT=30;

    public static String sTimeFormat = "yyyy-MM-dd HH:mm:ss";


    public static String getTimeNow(){
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(sTimeFormat);
        return simpleDateFormat.format(new Date());
    }
}
