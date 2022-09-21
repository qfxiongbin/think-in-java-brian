package com.brian.dp.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Date util
 *
 * @author BrianX
 * @date 2022/09/21 15:55
 **/
public class DateUtils {

    public static long getInterval(Date startDate, Date endDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        long start = calendar.getTimeInMillis();
        calendar.setTime(endDate);
        long end = calendar.getTimeInMillis();
        long interval = (end - start) / (1000*3600*24);
        return interval;
    }
}
