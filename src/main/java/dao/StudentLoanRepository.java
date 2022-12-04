package dao;

import dao.interfases.Repository;
import model.entity.StudentLoan;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class StudentLoanRepository implements Repository<StudentLoan> {
    private static StudentLoanRepository studentLoanRepository = new StudentLoanRepository();

    private StudentLoanRepository() {
    }

    public  static StudentLoanRepository getInstance() {
        return studentLoanRepository;
    }

    @Override
    public void save(StudentLoan studentLoan) {
        EntityManager entityManager = ConfigJpa.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(studentLoan);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void update(StudentLoan studentLoan) {
        EntityManager entityManager = ConfigJpa.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            String str="update   StudentLoan s set s.student=:student where s.student=:student and s.loan=:loan";
            Query query = entityManager.createQuery(str);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void delete(StudentLoan studentLoan) {

    }

    @Override
    public StudentLoan getById(int id) {
        return null;
    }
}
