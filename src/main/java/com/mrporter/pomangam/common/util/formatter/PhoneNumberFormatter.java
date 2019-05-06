package com.mrporter.pomangam.common.util.formatter;

public class PhoneNumberFormatter {

    public static String format(String phoneNumber) {
        if (phoneNumber == null) {
            return "";
        }
        String result = phoneNumber
                .replaceAll("-", "")
                .replaceAll("\\p{Z}", "");
        if (result.length() == 8) {
            return result.replaceFirst("^([0-9]{4})([0-9]{4})$", "$1-$2");
        } else if (result.length() == 12) {
            return result.replaceFirst("(^[0-9]{4})([0-9]{4})([0-9]{4})$", "$1-$2-$3");
        }
        return result.replaceFirst("(^02|[0-9]{3})([0-9]{3,4})([0-9]{4})$", "$1-$2-$3");
    }
}
