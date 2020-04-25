package com.healthyfoody.api.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConverterUtil {

    public static LocalDateTime toLocalDateTime(String string) {
        return LocalDateTime.parse(string, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public static String toString(Object obj) {
        return (obj == null) ? null : obj.toString();
    }
}
