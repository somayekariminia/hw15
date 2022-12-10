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

    public static boolean timeRegistryLoan(JalaliDate date) {
        if ((date.getMonthPersian().getValue() == 8 && (date.getDay() >= 1 && date.getDay() <= 8)) ||
                (((date.getMonthPersian().getValue() == 11 && (date.getDay() >= 25 && date.getDay() <= 30))) ||
                        (date.getMonthPersian().getValue() == 12 &&
                                date.getDay() >= 1 && date.getDay() <= 3)))
            return true;
        else
            return false;
    }

    public static LocalDate changeJalaliDateToMiladi(JalaliDate date) {
        DateConverter dateConverter = new DateConverter();
        LocalDate localDate1 = dateConverter.jalaliToGregorian(date);
        return localDate1;
    }

    public static JalaliDate changeMiladiDateToJalali(LocalDate localDate) {
        DateConverter dateConverter = new DateConverter();
        JalaliDate date = dateConverter.gregorianToJalali(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth());
        return date;
    }
    public static LocalDate incrementToMonth(LocalDate date){
        if(date.getMonthValue()==12)
            date=date.of(date.getYear()+1,1,date.getDayOfMonth());
        else
           date= date.of(date.getYear(),date.getMonthValue()+1, date.getDayOfMonth());
        return  date;
    }
}
