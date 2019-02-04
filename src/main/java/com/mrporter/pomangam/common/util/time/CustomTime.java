package com.mrporter.pomangam.common.util.time;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class CustomTime {

    public static String toTimeByDate(java.sql.Date date) {
        return date.toLocalDate().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public static String toTimeByDate(java.util.Date date) {
        return new SimpleDateFormat("HH:mm:ss").format(date.getTime());
    }
}
