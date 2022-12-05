package service;

import Exeption.ValidationException;
import Util.ValidationInfoCreditCard;
import dao.CreditCardRepository;
import model.entity.CreditCard;
import model.enumes.NameBank;

public class CreditCardService {
    CreditCardRepository creditCardRepository = CreditCardRepository.getInstance();

    public void save(CreditCard creditCard) {
        try {
            ValidationInfoCreditCard.checkCreditCard(creditCard.getCardNumber());
            ValidationInfoCreditCard.checkAccountNumber(creditCard.getAccountNumber());
            ValidationInfoCreditCard.checkExpirationDate(creditCard.getExpireDate());
            creditCardRepository.save(creditCard);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
    }
    public  NameBank findNameBank(String numberCard) {
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
