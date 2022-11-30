package Util;

import Exeption.ValidationException;

public class ValidationInputs {
    private static ValidationInputs validationInputs = new ValidationInputs();

    public static ValidationInputs getInstance() {
        return validationInputs;
    }

    private ValidationInputs() {
    }

    public String validationPassword(String password) {
        String password1 = null;
        if (password.matches("(?=.{8,}$)(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[0-9])(?=.*?[@,!,&,#,$]).*$"))
            password1 = password;
        else
            throw new ValidationException("yourPassword is invalid");
        if (password1.length() != 8)
            throw new IllegalArgumentException("length your password more 8");
        else
            return password1;
    }

    public String validationNationalCode(String nationalCode) {
        if (nationalCode.matches("[0-9]{10}"))
            return nationalCode;
        else
            throw new ValidationException("your nationalCode is invalid ");
    }

    public String validationNameAndLastName(String name) {
        if (name.matches("[a-zA-Z]+"))
            return name;
        else
            throw new ValidationException("name or lastName is invalid ");
    }

    public static String evaluateCreditCard(String numberCard) {
        if (numberCard.matches("[0-9]{16}"))
            return numberCard;
        else
            throw new ValidationException("numberCard isnt valid");
    }

    public static String evaluateAccountNumber(String accountNumber) {
        if (accountNumber.matches("[0-9]{10}"))
            return accountNumber;
        else
            throw new ValidationException("accountNumber isnt valid");
    }

    public String findNameBank(String numberCard) {
        if (numberCard.substring(0, 5).equals("589463"))
            return "Rafah";
        else if (numberCard.substring(0, 5).equals("627353"))
            return "Tajarat";
        else if (numberCard.substring(0, 5).equals("603799"))
            return "Melli";
        else if (numberCard.substring(0, 5).equals("628023"))
            return "maskan";
        else
            return null;
    }
}
