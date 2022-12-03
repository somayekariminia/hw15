package service;

import dao.StudentLoanRepository;
import model.entity.*;
import model.enumes.TypeLoan;

import java.time.LocalDateTime;
import java.util.Date;

public class StudentLoanService {
    StudentLoanRepository studentLoanRepository=StudentLoanRepository.getInstance();
    public void registry(Student student, Loan loan, Date registryDate) {
        StudentLoan studentLoan=new StudentLoan();
        studentLoan.setStudent(student);
        studentLoan.setLoan(loan);
        studentLoan.setCreditCard(student.getCreditCard());
        studentLoan.setReceiveDate(registryDate);
        studentLoanRepository.save(studentLoan);
      }

}