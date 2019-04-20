package com.mrporter.pomangam.common.util.time;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class CustomTime {

    public static String curDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
    }

    public static String curTime() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis()));
    }

    public static String curDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
    }

    public static java.sql.Date curDateTimeSql() {
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        return date;
    }

    public static java.sql.Timestamp curTimestampSql() {
        java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
        return timestamp;
    }

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

    public static boolean isPast(LocalDateTime date) {
        LocalDate today = LocalDate.now();
        return today.compareTo(date.toLocalDate()) > 0;
    }

    public static boolean isFuture(LocalDateTime date) {
        LocalDate today = LocalDate.now();
        return today.compareTo(date.toLocalDate()) < 0;
    }

    public static boolean isPast(ZonedDateTime date) {
        LocalDate today = LocalDate.now(date.getZone());
        return today.compareTo(date.toLocalDate()) > 0;
    }

    public static boolean isFuture(ZonedDateTime date) {
        LocalDate today = LocalDate.now(date.getZone());
        return today.compareTo(date.toLocalDate()) < 0;
    }

    // 과거:1 현재:0 미래:-1
    public static boolean isToday(ZonedDateTime date) {
        LocalDate today = LocalDate.now(date.getZone());
        return today.compareTo(date.toLocalDate()) == 0;
    }

    public static boolean isToday(java.sql.Date date) {
        LocalDate today = LocalDate.now();
        return today.compareTo(date.toLocalDate()) == 0;
    }

    public static int compareToday(java.sql.Date date) {
        LocalDate today = LocalDate.now();
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

        System.out.println(curTimestampSql());
    }
}
