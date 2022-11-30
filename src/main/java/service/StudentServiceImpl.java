package service;

import Util.ValidationInputs;
import dao.StudentLoanRepository;
import dao.StudentRepository;
import model.entity.GrantLoan;
import model.entity.Student;
import model.enumes.TypeLoan;

public class StudentServiceImpl {
    StudentRepository studentRepository = StudentRepository.getInstance();
    ValidationInputs validationInputs = ValidationInputs.getInstance();
    StudentLoanRepository studentLoanRepository = StudentLoanRepository.getInstance();

    public void signUp(Student student) {
        String firstName = validationInputs.validationNameAndLastName(student.getFirstName());
        String lastName = validationInputs.validationNameAndLastName(student.getLastName());
        String nationalCode = validationInputs.validationNationalCode(student.getNationalCode());
        String password = validationInputs.validationPassword(student.getInfoAccount().getPassword());
        if (firstName != null && lastName != null && nationalCode != null && password != null)
            studentRepository.save(student);
        else
            throw new RuntimeException("yourInformation is inValid");
    }

    public Student signIn(String userName, String password) {
        String passwordStudent = validationInputs.validationPassword(password);
        return studentRepository.findByUserNameAndPassword(userName, passwordStudent);
    }



    public void registerLoan(Student student) {
        //todo
    }

    public boolean graduate(Student student) {
        //todo
        return false;
    }
}
