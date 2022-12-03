package service;

import Util.ValidationInfoCreditCard;
import dao.CreditCardRepository;
import model.entity.CreditCard;

public class CreditCardService {
    CreditCardRepository creditCardRepository=CreditCardRepository.getInstance();
    public void save(CreditCard creditCard)
    {
        String cardNumber=ValidationInfoCreditCard.checkCreditCard(creditCard.getCardNumber());
        String accountNumber=ValidationInfoCreditCard.checkAccountNumber(creditCard.getAccountNumber());
        boolean expireDate=ValidationInfoCreditCard.checkExpirationِDate(creditCard.getExpireDate());
        if(cardNumber!=null && accountNumber!=null && expireDate==true)
            creditCardRepository.save(creditCard);
    }
}
