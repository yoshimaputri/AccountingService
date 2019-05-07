package com.service.accounting.utils;

import com.service.accounting.exception.InputFormatException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
    private InputValidator() { }

    public static void checkValidDate(String date) {
        boolean status;
        Pattern datePattern = Pattern.compile("((19|20)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])");
        Matcher matcher = datePattern.matcher(date);

        if (matcher.matches()) {
            int year = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(3));
            int day = Integer.parseInt(matcher.group(4));

            if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) {
                status =  false;
            } else if (month == 2) {
                if (day >= 30) {
                    status = false;
                }
                else status = day != 29 || (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0));
            } else {
                status = true;
            }
        } else {
            status = false;
        }

        if (!status) {
            throw new InputFormatException("Invalid date format. Expected 'YYYY-MM-DD'.");
        }
    }

    public static void checkValidKeterangan(String keterangan) {
        if (keterangan.isEmpty() || keterangan.length() > 200) {
            throw new InputFormatException("The field 'keterangan' exceeds maximum length (200 characters)");
        }
    }
}
