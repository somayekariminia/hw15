package Util;

import Exeption.ValidationException;
import model.enumes.NameBank;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;

public class ValidationInfoCreditCard {
    public static String checkCreditCard(String numberCard) {
        if (numberCard.matches("[0-9]{16}"))
            return numberCard;
        else
            throw new ValidationException("numberCard isnt valid");
    }

    public static String checkAccountNumber(String accountNumber) {
        if (accountNumber.matches("[0-9]{10}"))
            return accountNumber;
        else
            throw new ValidationException("accountNumber isnt valid");
    }

    public static boolean checkExpirationِDate(Date expirationDate) {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime expirationLDate = UtilDate.getLocalDateTime(expirationDate);
        if (today.getYear() <= expirationLDate.getYear() &&
                today.getMonth().ordinal() <= expirationLDate.getMonth().ordinal())
            return true;
        else
            return false;
    }

    public NameBank findNameBank(String numberCard) {
        String subString = numberCard.substring(0, 6);
        if (subString.equals("589463"))
            return NameBank.RAFAH;
        else if (subString.equals("627353"))
            return NameBank.TAJARAT;
        else if (subString.equals("603799"))
            return NameBank.MELLI;
        else if (subString.equals("628023"))
            return NameBank.MASKAN;
        else
            return null;
    }
}
