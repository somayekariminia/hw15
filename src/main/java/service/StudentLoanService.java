package service;

import Exeption.ValidationException;
import Util.ValidationInfoCreditCard;
import com.github.eloyzone.jalalicalendar.JalaliDate;
import dao.CreditCardRepository;
import dao.StudentLoanRepository;
import model.entity.*;
import model.enumes.NameBank;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class StudentLoanService {
    StudentLoanRepository studentLoanRepository = StudentLoanRepository.getInstance();
    CreditCardRepository creditCardRepository = CreditCardRepository.getInstance();
    CreditCardService creditCardService = new CreditCardService();
    InstallmentsService installmentsService = new InstallmentsService();
    StudentServiceImpl studentService = new StudentServiceImpl();

    public void registryLoan(Student student, Loan loan, Date registryDate, String lease, CreditCard creditCard) {
        StudentLoan studentLoan = new StudentLoan();
        studentLoan.setStudent(student);
        studentLoan.setLoan(loan);
        studentLoan.setCreditCard(creditCard);
        studentLoan.setReceiveDate(registryDate);
        ValidationInfoCreditCard.checkCreditCard(creditCard.getCardNumber());
        ValidationInfoCreditCard.checkExpirationDate(creditCard.getExpireDate());
        ValidationInfoCreditCard.checkAccountNumber(creditCard.getAccountNumber());
        NameBank nameBank = creditCardService.findNameBank(creditCard.getCardNumber());
        if (Arrays.stream(NameBank.values()).anyMatch(value -> value.equals(nameBank)))
            studentLoan.setCreditCard(creditCard);
        else
            throw new ValidationException("please numberAccount of bank( MASKAN,TAJARAT,RAFAH,MELLI)");
        int graduate = studentService.graduate(student);
        JalaliDate jalaliDate = new JalaliDate(graduate, 06, 31);
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
            if (Arrays.stream(NameBank.values()).anyMatch(value -> value.equals(nameBank)))
                creditCard.setBalance(loan.getAmount());
            creditCardRepository.equals(creditCardRepository);
        } catch (ValidationException e) {
            System.err.println(e.getMessage());
        }
    }

    public StudentLoan getByStudentLoan(Student student, Loan loan) {
        return studentLoanRepository.getByIdStudentLoan(student, loan);
    }

    public List<StudentLoan> getAlLoansStudent(Student student) {
        return studentLoanRepository.getByIdStudent(student);
    }

}