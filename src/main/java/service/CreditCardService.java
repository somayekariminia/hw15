package service;

import Exeption.ValidationException;
import Util.ValidationInfoCreditCard;
import dao.CreditCardRepository;
import model.entity.CreditCard;

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
}
