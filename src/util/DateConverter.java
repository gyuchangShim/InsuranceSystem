package util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateConverter {

    public static String toString(LocalDate localDate) {
        return localDate == null ? null : "'" + localDate + "'";
    }

    public static String toString(LocalDateTime localDateTime) {
        return localDateTime == null ? null : "'" + localDateTime + "'";
    }
}
