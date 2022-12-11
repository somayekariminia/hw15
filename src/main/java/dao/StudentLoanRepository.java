package dao;

import dao.interfases.Repository;
import model.entity.Loan;
import model.entity.Student;
import model.entity.StudentLoan;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class StudentLoanRepository implements Repository<StudentLoan> {
    private static StudentLoanRepository studentLoanRepository = new StudentLoanRepository();

    private StudentLoanRepository() {
    }

    public static StudentLoanRepository getInstance() {
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
            String str = "update   StudentLoan s set s.installments=:installments where s.student=:student and s.loan=:loan";
            Query query = entityManager.createQuery(str);
            query.setParameter("student", studentLoan.getStudent());
            query.setParameter("loan", studentLoan.getLoan());
            query.setParameter("installments",studentLoan.getInstallments());
            query.executeUpdate();
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void delete(StudentLoan studentLoan) {
        EntityManager entityManager = ConfigJpa.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("delete StudentLoan s where s.student=:student and s.loan=:loan");
            query.setParameter("student", studentLoan.getStudent());
            query.setParameter("loan", studentLoan.getLoan());
            query.executeUpdate();
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public StudentLoan getById(int id) {
        return null;
    }

    public StudentLoan getByIdStudentLoan(Student student, Loan loan) {
        StudentLoan studentLoan = null;
        EntityManager entityManager = ConfigJpa.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("from StudentLoan s where s.student=:student and s.loan=:loan");
            query.setParameter("student", student);
            query.setParameter("loan", loan);
            studentLoan = (StudentLoan) query.getSingleResult();
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return studentLoan;

    }

    public List<StudentLoan> getByIdStudent(Student student) {
        List<StudentLoan> resultList = new ArrayList<>();
        EntityManager entityManager = ConfigJpa.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("from StudentLoan s where s.student=:student", StudentLoan.class);
            query.setParameter("student", student);
            resultList = query.getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return resultList;
    }
}
