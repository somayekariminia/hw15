package dao;

import model.entity.Student;

public class StudentRepository extends PersonRepository<Student> {
    private static StudentRepository studentRepository = new StudentRepository();

    private StudentRepository() {
    }

    public StudentRepository getInstance() {
        return studentRepository;
    }

}

