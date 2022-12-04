package service;

import Util.ValidationInputs;
import dao.StudentLoanRepository;
import dao.StudentRepository;
import model.entity.GrantLoan;
import model.entity.Person;
import model.entity.Student;
import model.enumes.TypeLoan;

public class StudentServiceImpl {
   private StudentRepository studentRepository = StudentRepository.getInstance();
     private ValidationInputs validationInputs = ValidationInputs.getInstance();
    private StudentLoanRepository studentLoanRepository = StudentLoanRepository.getInstance();

    public void signUp(Student student) {
      /* // String password = validationInputs.validationPassword(student.getInfoAccount().getPassword());
        if ( password != null)
            studentRepository.save(student);
        else
            throw new RuntimeException("yourInformation is inValid");*/
        studentRepository.save(student);
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

    public Student findById(int id) {
      return   studentRepository.getById(id);
    }
}
