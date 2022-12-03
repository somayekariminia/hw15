package service;

import model.entity.CreditCard;
import model.entity.Student;

import java.util.Date;
import java.util.List;

public interface LoanService<T> {


    void WithdrawLoan(Student student, CreditCard creditCard);

    boolean isRegistryStudentForLoan(Student student);

    void calculateRepaymentsLoan(T t);

    void paymentOfInstallments();

    List<Date> InstallmentsPaid(Student student);

    List<Date> notInstallmentsPaid(Student student);

    void timeStartPaymentOfInstallments(Student student);

}
