package Util;

import com.github.eloyzone.jalalicalendar.DateConverter;
import com.github.eloyzone.jalalicalendar.JalaliDate;

import java.time.*;
import java.util.Date;

public class UtilDate {

    public static Date changeLocalDateToDate(LocalDate localDate) {
        LocalDateTime localDateTime = localDate.atStartOfDay();
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        Date output = Date.from(zdt.toInstant());
        return output;
    }

    public static LocalDate getLocalDateTime(Date date) {
        LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return ldt.toLocalDate();
    }

    public static long differentDays(LocalDateTime localDateTime1, LocalDateTime localDateTime2) {

        long daysBetween = Math.abs(Duration.between(localDateTime1, localDateTime2).toDays());
        return daysBetween;
    }

    public static boolean timeRegistryLoan(LocalDate date) {
        if ((date.getMonthValue() == 10 && (date.getDayOfMonth() >= 21 && date.getDayOfMonth() <= 28)) || ((date.getMonthValue() == 2 && (date.getDayOfMonth() >= 13 && date.getDayOfMonth() <= 20))))
            return true;
        else
            return false;
    }

    public static LocalDate changeJalaliDateToMiladi(JalaliDate date) {
        DateConverter dateConverter = new DateConverter();
        LocalDate localDate1 = dateConverter.jalaliToGregorian(date);
        return localDate1;
    }

}
