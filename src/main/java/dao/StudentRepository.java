package dao;

import model.entity.Student;

public class StudentRepository extends PersonRepository<Student> {
    private static StudentRepository studentRepository=new StudentRepository();
    public  StudentRepository getInstance(){
        return studentRepository;
    }
    private StudentRepository() {
    }
}

