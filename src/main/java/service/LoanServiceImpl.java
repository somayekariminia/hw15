package service;

import Util.UtilDate;
import model.entity.CreditCard;
import model.entity.GrantLoan;
import model.entity.Student;
import model.enumes.Degree;
import model.enumes.TypeLoan;
import model.enumes.TypePayment;
import model.enumes.TypeUniversity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class LoanServiceImpl implements LoanService<GrantLoan> {

    @Override
    public void timeRegistryLoan(Date date) {

    }

    public void registryLoan(Student student, GrantLoan grantLoan) {
        double amount = 0;
        LocalDateTime today = LocalDateTime.now();
        if (UtilDate.timeRegistryLoan(today)) {
            if (grantLoan.getTypeLoan().equals(TypeLoan.EDUCATION)) {

                {
                    if (student.getDegree().equals(Degree.Bachelor))
                        amount = 19e5;
                    else if (student.getDegree().equals(Degree.Master))
                        amount = 225e4;
                    else if (student.getDegree().equals(Degree.PHD))
                        amount = 26e5;
                    grantLoan.setAmount(amount);
                    grantLoan.setTypePayment(TypePayment.HALFYEAR);
                }

            } else if (grantLoan.getTypeLoan().equals(TypeLoan.TUITION)) {
                if (!student.getUniversity().getTypeUniversity().equals(TypeUniversity.StateUniversityِDaily)) {
                    if (student.getDegree().equals(Degree.Bachelor))
                        amount = 13e5;
                    else if (student.getDegree().equals(Degree.Master))
                        amount = 26e5;
                    else if (student.getDegree().equals(Degree.PHD))
                        amount = 65e5;
                }
                grantLoan.setAmount(amount);
                grantLoan.setTypePayment(TypePayment.HALFYEAR);
            } else
                throw new RuntimeException("dont give loan to student StateUniversityDail");
        }
    }

    @Override
    public void WithdrawLoan(Student student, CreditCard creditCard) {

    }

    @Override
    public boolean isRegistryStudentForLoan(Student student) {
        return false;
    }

    @Override
    public void calculateRepaymentsLoan(model.entity.GrantLoan grantLoan) {

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
