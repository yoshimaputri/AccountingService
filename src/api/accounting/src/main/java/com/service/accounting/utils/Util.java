package com.service.accounting.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    private Util() { }

    public static boolean isValidDate(String date) {
        Pattern datePattern = Pattern.compile("((19|20)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])");
        Matcher matcher = datePattern.matcher(date);

        if (matcher.matches()) {
            int year = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(3));
            int day = Integer.parseInt(matcher.group(4));

            if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) {
                return false;
            } else if (month == 2) {
                if (day >= 30) {
                    return false;
                }
                else return day != 29 || (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0));
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
