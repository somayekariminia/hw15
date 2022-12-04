package dao;

import model.entity.Person;
import model.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class StudentRepository extends PersonRepository<Student> {
    private static StudentRepository studentRepository = new StudentRepository();

    private StudentRepository() {
    }

    public static StudentRepository getInstance() {
        return studentRepository;
    }

    public Student findByUserNameAndPassword(String userName, String passwordStudent) {
        EntityManager entityManager = ConfigJpa.getInstance().createEntityManager();
        Student student = null;
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("from Student s where s.infoAccount.password=:password" +
                    " and s.infoAccount.userName=:userName");
            query.setParameter("userName", userName);
            query.setParameter("password", passwordStudent);
            student = (Student) query.getSingleResult();
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return student;
    }
    @Override
    public Student getById(int id) {
        Student student=null;
        EntityManager entityManager = ConfigJpa.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            student = entityManager.find(Student.class, id);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return student ;
    }
    public  Student getByNationalCode(String nationalCode){
        Student student=null;
        EntityManager entityManager = ConfigJpa.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            student = (Student) entityManager.createQuery("from Student s where s.nationalCode=:nationalCode")
                    .setParameter("nationalCode", nationalCode).getSingleResult();
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return student;
    }
}

