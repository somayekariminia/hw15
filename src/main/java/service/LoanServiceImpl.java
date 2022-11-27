package service;

import model.entity.CreditCard;
import model.entity.Student;

import java.util.Date;
import java.util.List;

public class LoanServiceImpl<T> implements LoanService<T> {
    @Override
    public void timeRegistryLoan() {

    }

    @Override
    public void WithdrawLoan(Student student, CreditCard creditCard) {

    }

    @Override
    public boolean isRegistryStudentForLoan(Student student) {
        return false;
    }

    @Override
    public void calculateRepaymentsLoan(T t) {

    }

    @Override
    public void paymentOfInstallments() {

    }

    @Override
    public List<Date> InstallmentsPaid(Student student) {
        return null;
    }

    @Override
    public List<Date> notInstallmentsPaid(Student student) {
        return null;
    }

    @Override
    public void timeStartPaymentOfInstallments(Student student) {

    }
}
