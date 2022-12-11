package service;

import Exeption.ValidationException;
import Util.ValidationInfoCreditCard;
import com.github.eloyzone.jalalicalendar.JalaliDate;
import dao.CreditCardRepository;
import dao.StudentLoanRepository;
import model.entity.*;
import model.enumes.NameBank;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class StudentLoanService {
    StudentLoanRepository studentLoanRepository = StudentLoanRepository.getInstance();
    CreditCardRepository creditCardRepository = CreditCardRepository.getInstance();
    CreditCardService creditCardService = new CreditCardService();
    InstallmentsService installmentsService=new InstallmentsService();
    public void registryLoan(Student student, Loan loan, Date registryDate, String lease) {
        StudentLoan studentLoan = new StudentLoan();
        studentLoan.setStudent(student);
        studentLoan.setLoan(loan);
        studentLoan.setCreditCard(student.getCreditCard());
        studentLoan.setReceiveDate(registryDate);
        JalaliDate jalaliDate=new JalaliDate(1396,6,31);
        Set<Installments> installments = installmentsService.calculateInstallments(studentLoan, jalaliDate);
        studentLoan.setInstallments(installments);
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
    public StudentLoan getByStudentLoan(Student student,Loan loan){
        return studentLoanRepository.getByIdStudentLoan(student,loan);
    }

    public List<StudentLoan> getAlLoansStudent(Student student) {
        return studentLoanRepository.getByIdStudent(student);
    }

}