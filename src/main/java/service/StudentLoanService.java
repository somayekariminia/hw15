package service;

import Exeption.ValidationException;
import Util.ValidationInfoCreditCard;
import dao.CreditCardRepository;
import dao.StudentLoanRepository;
import model.entity.*;
import model.enumes.NameBank;

import java.util.Date;

public class StudentLoanService {
    StudentLoanRepository studentLoanRepository = StudentLoanRepository.getInstance();
    CreditCardRepository creditCardRepository = CreditCardRepository.getInstance();
    CreditCardService creditCardService = new CreditCardService();

    public void registryLoan(Student student, Loan loan, Date registryDate, String lease) {
        StudentLoan studentLoan = new StudentLoan();
        studentLoan.setStudent(student);
        studentLoan.setLoan(loan);
        studentLoan.setCreditCard(student.getCreditCard());
        studentLoan.setReceiveDate(registryDate);
        if (loan instanceof MortgageLoan)
            studentLoan.setLease(lease);
        studentLoanRepository.save(studentLoan);
    }

    public void WithdrawLoan(Student student, CreditCard creditCard, Loan loan) {
        try {
            ValidationInfoCreditCard.checkCreditCard(creditCard.getCardNumber());
            ValidationInfoCreditCard.checkExpirationDate(creditCard.getExpireDate());
            ValidationInfoCreditCard.checkAccountNumber(creditCard.getAccountNumber());
            NameBank nameBank = creditCardService.findNameBank(creditCard.getCardNumber());
            creditCard.setBalance(loan.getAmount());
            creditCardRepository.equals(creditCardRepository);
        } catch (ValidationException e) {
            System.err.println(e.getMessage());
        }
    }

}