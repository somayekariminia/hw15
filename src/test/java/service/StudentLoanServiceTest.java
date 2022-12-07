package service;

import dao.StudentLoanRepository;
import dao.StudentRepository;
import model.entity.Student;
import model.entity.StudentLoan;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;

class StudentLoanServiceTest {
    StudentLoanRepository studentLoanRepository = StudentLoanRepository.getInstance();
    StudentRepository studentRepository = StudentRepository.getInstance();

    @Test
    void getAllLoanStudent() {
        Student student = studentRepository.getById(3);
        List<StudentLoan> loans = studentLoanRepository.getById(student);
        assertNotNull(loans);
    }
}