package Util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class UtilDate {

    public static Date changeLocalDateToDate(LocalDateTime localDate) {
        ZonedDateTime zdt = localDate.atZone(ZoneId.systemDefault());
        Date output = Date.from(zdt.toInstant());
        return output;
    }

    public static LocalDateTime getLocalDateTime(Date date) {
        LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return ldt;
    }

    public  static long differentDays(LocalDateTime localDateTime1, LocalDateTime localDateTime2) {

        long daysBetween =Math.abs(Duration.between(localDateTime1, localDateTime2).toDays());
        return daysBetween;
    }
}
