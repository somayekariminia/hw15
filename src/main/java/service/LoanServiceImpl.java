package service;

import Util.UtilDate;
import com.github.eloyzone.jalalicalendar.JalaliDate;
import dao.GrantLoanRepository;
import dao.LoanRepository;
import model.entity.CreditCard;
import model.entity.GrantLoan;
import model.entity.Student;
import model.enumes.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class LoanServiceImpl implements LoanService<GrantLoan> {
    LoanRepository<GrantLoan> grantLoanRepository = GrantLoanRepository.getInstance();
    StudentLoanService studentLoanService = new StudentLoanService();

    public void requestForGrandLoan(Student student, GrantLoan grantLoan, JalaliDate dateRegistry) {
        TypeCity typeCity;
        double amount = 0;
        LocalDate today = UtilDate.changeJalaliDateToMiladi(dateRegistry);
        if (UtilDate.timeRegistryLoan(today)) {

            if (grantLoan.getTypeLoan().equals(TypeLoan.EDUCATION)) {
                if (student.getStudentLoanList().stream().anyMatch(studentLoan -> studentLoan.getReceiveDate().getYear() == today.getYear()))
                    throw new RuntimeException("registry before loanEducation");
                else {
                    if (student.getDegree().equals(Degree.ContinueBachelor))
                        amount = 19e5;
                    else if (student.getDegree().equals(Degree.ContinueMaster))
                        amount = 225e4;
                    else if (student.getDegree().equals(Degree.ContinuePHD))
                        amount = 26e5;
                    grantLoan.setAmount(amount);
                    grantLoan.setTypePayment(TypePayment.HALFYEAR);
                    grantLoan.setDegree(student.getDegree());
                    grantLoan.setTypeLoan(TypeLoan.EDUCATION);
                    grantLoanRepository.save(grantLoan);
                    studentLoanService.registryLoan(student, grantLoan, UtilDate.changeLocalDateToDate(today), null);
                }

            } else if (grantLoan.getTypeLoan().equals(TypeLoan.TUITION)) {
                if (student.getStudentLoanList().stream().anyMatch(studentLoan -> studentLoan.getReceiveDate().getYear() == today.getYear()))
                    throw new RuntimeException("before Registry loan TuitionLoan");
                else {
                    if (!student.getUniversity().getTypeUniversity().equals(TypeUniversity.StateUniversityِDaily)) {
                        if (student.getDegree().equals(Degree.ContinueBachelor))
                            amount = 13e5;
                        else if (student.getDegree().equals(Degree.ContinueMaster))
                            amount = 26e5;
                        else if (student.getDegree().equals(Degree.ContinuePHD))
                            amount = 65e5;

                        grantLoan.setAmount(amount);
                        grantLoan.setTypePayment(TypePayment.HALFYEAR);
                        grantLoan.setDegree(student.getDegree());
                        grantLoan.setTypeLoan(TypeLoan.EDUCATION);
                        grantLoanRepository.save(grantLoan);
                        studentLoanService.registryLoan(student, grantLoan, UtilDate.changeLocalDateToDate(today), null);
                    }
                }
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
