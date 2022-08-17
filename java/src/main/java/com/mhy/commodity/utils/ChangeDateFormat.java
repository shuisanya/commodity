package com.mhy.commodity.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChangeDateFormat {

    private static final String FORMAT_Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss";
    private static final String FORMAT_Y_M_D = "yyyy-MM-dd";


    public static String getDateFormatYMDHMS(Date date){
        Date endTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_Y_M_D_H_M_S);
        return simpleDateFormat.format(endTime);
    }

    public static String getDateFormatYMD(Date date){
        Date endTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_Y_M_D);
        return simpleDateFormat.format(endTime);
    }


}
