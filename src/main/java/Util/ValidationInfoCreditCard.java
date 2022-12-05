package Util;

import Exeption.ValidationException;
import model.enumes.NameBank;

import java.time.LocalDateTime;
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

    public static boolean checkExpirationDate(Date expirationDate) {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime expirationLDate = UtilDate.getLocalDateTime(expirationDate).atStartOfDay();
        if (today.getYear() <= expirationLDate.getYear() &&
                today.getMonth().ordinal() <= expirationLDate.getMonth().ordinal())
            return true;
        else
            return false;
    }


}
