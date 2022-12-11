package service;

import com.github.eloyzone.jalalicalendar.JalaliDate;
import dao.GrantLoanRepository;
import dao.LoanRepository;
import dao.StudentLoanRepository;
import dao.StudentRepository;
import model.entity.*;
import org.junit.jupiter.api.Test;

import java.util.List;

class LoanServiceImplTest {
    InstallmentsService installmentsService = new InstallmentsService();
    StudentLoanRepository studentLoanRepository = StudentLoanRepository.getInstance();
    StudentRepository studentRepository = StudentRepository.getInstance();
    LoanRepository<GrantLoan> loanRepository = GrantLoanRepository.getInstance();

    @Test
    void calculateInstallmentsTest() {
        Loan loan = loanRepository.getById(1);
        Student student = studentRepository.getById(2);
        StudentLoan studentLoan = studentLoanRepository.getByIdStudent(student, loan);
        JalaliDate date = new JalaliDate(1396, 06, 01);
        installmentsService.calculateInstallments(studentLoan, date);
    }

    @Test
    void getAllInstallments() {
        Loan loan = loanRepository.getById(1);
        Student student = studentRepository.getById(2);
        StudentLoan studentLoan = studentLoanRepository.getByIdStudent(student, loan);
        // Set<Installments> installments = studentLoan.getInstallments();
        List<Installments> installments = installmentsService.allInstallment(studentLoan);
    }
}