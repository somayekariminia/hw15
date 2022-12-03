package Util;

import Exeption.ValidationException;
import model.enumes.NameBank;

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
            throw new ValidationException("length your password more 8");
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
}
