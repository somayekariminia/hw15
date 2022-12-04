package service;

import dao.StudentLoanRepository;
import model.entity.*;
import model.enumes.TypeLoan;

import java.time.LocalDateTime;
import java.util.Date;

public class StudentLoanService {
    StudentLoanRepository studentLoanRepository=StudentLoanRepository.getInstance();
    public void registryLoan(Student student, Loan loan, Date registryDate,String lease) {
        StudentLoan studentLoan=new StudentLoan();
        studentLoan.setStudent(student);
        studentLoan.setLoan(loan);
        studentLoan.setCreditCard(student.getCreditCard());
        studentLoan.setReceiveDate(registryDate);
        if(loan instanceof MortgageLoan)
            studentLoan.setLease(lease);
        studentLoanRepository.save(studentLoan);
      }

}