package com.brian.dp.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {

    @Test
    public void getIntervalTest(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            Date d1 = sdf.parse("20220922");
            Date d2 = sdf.parse("20220920");
            Assertions.assertEquals(2,DateUtils.getInterval(d2,d1));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }


}