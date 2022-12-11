package service;

import com.github.eloyzone.jalalicalendar.JalaliDate;
import dao.GrantLoanRepository;
import dao.LoanRepository;
import dao.StudentLoanRepository;
import dao.StudentRepository;
import model.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;

class InstallmentTests {
    InstallmentsService installmentsService = new InstallmentsService();
    StudentLoanRepository studentLoanRepository = StudentLoanRepository.getInstance();
    StudentRepository studentRepository = StudentRepository.getInstance();
    LoanRepository<GrantLoan> loanRepository = GrantLoanRepository.getInstance();

    @Test
    void calculateInstallmentsTest() {
        Loan loan = loanRepository.getById(1);
        Student student = studentRepository.getById(2);
        StudentLoan studentLoan = studentLoanRepository.getByIdStudentLoan(student, loan);
        JalaliDate date = new JalaliDate(1396, 06, 01);
        installmentsService.calculateInstallments(studentLoan, date);
        Assertions.assertFalse(studentLoan.getInstallments().isEmpty());
    }

    @Test
    void getAllInstallments() {
        Loan loan = loanRepository.getById(1);
        Student student = studentRepository.getById(2);
        StudentLoan studentLoan = studentLoanRepository.getByIdStudentLoan(student, loan);
        // Set<Installments> installments = studentLoan.getInstallments();
        List<Installments> installments = installmentsService.allInstallment(studentLoan);
        Assertions.assertFalse(installments.isEmpty());
    }

    @Test
    void PaidInstallmentsTest() {
        Installments installments = installmentsService.paidInstallments(2);
        assertNotNull(installments);
    }

    @Test
    void installmentIsNotPaid() {
        Loan loan = loanRepository.getById(1);
        Student student = studentRepository.getById(2);
        StudentLoan studentLoan = studentLoanRepository.getByIdStudentLoan(student, loan);
        List<Installments> paidInstallments = installmentsService.isNotPaidInstallments(studentLoan);
        Assertions.assertFalse(paidInstallments.isEmpty());
    }

    @Test
    void installmentIstPaid() {
        Loan loan = loanRepository.getById(1);
        Student student = studentRepository.getById(2);
        StudentLoan studentLoan = studentLoanRepository.getByIdStudentLoan(student, loan);
        List<Installments> paidInstallments = installmentsService.isPaidInstallments(studentLoan);
        Assertions.assertFalse(paidInstallments.isEmpty());
    }
}
