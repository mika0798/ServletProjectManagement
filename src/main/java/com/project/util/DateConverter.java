package com.project.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateConverter {
    public static java.sql.Date convertStringToDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = null;
        try {
            localDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException dtpe) {
            System.out.println("Invalid date format: " + date + ".");
        }
        return java.sql.Date.valueOf(localDate);
    }
}
