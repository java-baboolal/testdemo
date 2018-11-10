package com.oauth.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;

public class RESTSecurityDateTimeUtil {

    public static Date addHoursToDate(Integer hour) {
        return getDate(hour);
    }

    public static Date getDate(Integer hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR_OF_DAY, hour);
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));
        sdf.setCalendar(cal);
        return sdf.getCalendar().getTime();
    }

    public static Date getCurrentDate() {
        return getDate(0);
    }
}
