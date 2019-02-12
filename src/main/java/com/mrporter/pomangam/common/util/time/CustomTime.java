package com.mrporter.pomangam.common.util.time;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class CustomTime {

    public static String toTimeByDate(java.sql.Date date) {
        return date.toLocalDate().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public static String toTimeOnlyByDate(java.util.Date date) {
        return new SimpleDateFormat("HH:mm:ss").format(date.getTime());
    }

    public static String toDateOnlyByDate(java.util.Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date.getTime());
    }

    public static long getMinuteByCurrentTimeDifference(ZonedDateTime endTimeWithZone) {
        final ZonedDateTime zonedDateTime = ZonedDateTime.of(
                LocalDateTime.now(),
                endTimeWithZone.getZone());
        long minutes = ChronoUnit.MINUTES.between(zonedDateTime, endTimeWithZone);
        return minutes;
    }

    public static int isToday(ZonedDateTime date) {
        LocalDate today = LocalDate.now(date.getZone());
        return today.compareTo(date.toLocalDate());
    }

    @Deprecated
    public static int getMinuteByTimeDifference(String t1, String t2) {
        int min1 = Integer.parseInt(t1.split(":")[0]) * 60 + Integer.parseInt(t1.split(":")[1]);
        int min2 = Integer.parseInt(t2.split(":")[0]) * 60 + Integer.parseInt(t2.split(":")[1]);
        return min1 > min2
                ? min1 - min2
                : min2 - min1;
    }

    public static void main(String...args) {
        //System.out.println(CustomTime.getMinuteByTimeDifference("11:40:00", "12:00:00"));
        //System.out.println(CustomTime.toTimeOnlyByDate(Calendar.getInstance().getTime()));
        //System.out.println(CustomTime.getMinuteByTimeDifference(CustomTime.toTimeOnlyByDate(Calendar.getInstance().getTime()), "4:40:00"));
        //getMinuteByCurrentTimeDifference("", "");
        final LocalDateTime localDateTime = LocalDateTime.now();
        final ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("Asia/Seoul"));
        System.out.println(localDateTime);
    }
}
