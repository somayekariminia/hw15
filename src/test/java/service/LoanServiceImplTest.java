package service;

import com.github.eloyzone.jalalicalendar.JalaliDate;
import dao.GrantLoanRepository;
import dao.LoanRepository;
import dao.StudentLoanRepository;
import dao.StudentRepository;
import model.entity.GrantLoan;
import model.entity.Loan;
import model.entity.Student;
import model.entity.StudentLoan;
import org.junit.jupiter.api.Test;

class LoanServiceImplTest {
    InstallmentsService installmentsService = new InstallmentsService();
    StudentLoanRepository studentLoanRepository = StudentLoanRepository.getInstance();
    StudentRepository studentRepository=StudentRepository.getInstance();
    LoanRepository<GrantLoan>loanRepository= GrantLoanRepository.getInstance();

    @Test
    void calculateInstallmentsTest() {
        Loan loan=loanRepository.getById(1);
        Student student=studentRepository.getById(2);
        StudentLoan studentLoan = studentLoanRepository.getByIdStudent(student,loan);
        JalaliDate date = new JalaliDate(1396, 06, 01);
        installmentsService.calculateInstallments(studentLoan, date);
    }
}