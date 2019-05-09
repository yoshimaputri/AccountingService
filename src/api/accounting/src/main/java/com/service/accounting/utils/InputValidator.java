package com.service.accounting.utils;

import com.service.accounting.exception.InputFormatException;
import com.service.accounting.model.Pendapatan;
import com.service.accounting.model.Pengeluaran;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
    private InputValidator() { }

    private static void checkValidDate(String date) {
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

    private static void checkValidKeterangan(String keterangan) {
        if (keterangan.isEmpty()) {
            throw new InputFormatException("Field 'keterangan' cannot be null or empty");
        }
        if (keterangan.length() > 200) {
            throw new InputFormatException("Field 'keterangan' exceeds maximum length (200 characters)");
        }
    }

    public static void validateInputData(Pendapatan partialValue, boolean insertMode) {
        if (insertMode) {
            if (partialValue.getJumlah() == null) {
                throw new InputFormatException("Field 'jumlah' cannot be null or empty");
            }
            if (partialValue.getTanggal() == null) {
                throw new InputFormatException("Field 'tanggal' cannot be null or empty");
            }
            checkValidDate(partialValue.getTanggal());
        } else {
            if (partialValue.getTanggal() == null && partialValue.getJumlah() == null) {
                throw new InputFormatException("At least 1 field must contains value");
            }
            if (partialValue.getTanggal() != null) {
                checkValidDate(partialValue.getTanggal());
            }
        }
    }

    public static void validateInputData(Pengeluaran partialValue, boolean insertMode) {
        if (insertMode) {
            if (partialValue.getJumlah() == null) {
                throw new InputFormatException("Field 'jumlah' cannot be null or empty");
            }
            if (partialValue.getTanggal() == null) {
                throw new InputFormatException("Field 'tanggal' cannot be null or empty");
            }
            if (partialValue.getKeterangan() == null) {
                throw new InputFormatException("Field 'keterangan' cannot be null or empty");                
            }
            checkValidDate(partialValue.getTanggal());
            checkValidKeterangan(partialValue.getKeterangan());
        } else {
            if (partialValue.getTanggal() == null && partialValue.getJumlah() == null 
                    && partialValue.getKeterangan() == null) {
                throw new InputFormatException("At least 1 field must contains value");
            }
            if (partialValue.getTanggal() != null) {
                checkValidDate(partialValue.getTanggal());
            }
            if (partialValue.getKeterangan() != null) {
                checkValidKeterangan(partialValue.getKeterangan());
            }
        }
    }

    public static void checkValidPath(String year, String month) {
        if (year != null && !year.matches("(19|20)\\d\\d")) {
            throw new InputFormatException("Year should be in YYYY format.");
        }
        if (month != null && !month.matches("0[1-9]|1[012]")) {
            throw new InputFormatException("Month should be in MM format.");
        }
    }
}
