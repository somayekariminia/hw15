package dao;

import model.entity.Installments;
import model.entity.StudentLoan;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class InstallmentsRepository {
    private static InstallmentsRepository installmentsRepository = new InstallmentsRepository();

    private InstallmentsRepository() {
    }

    public static InstallmentsRepository getInstance() {
        return installmentsRepository;
    }

    public void save(Installments installments, EntityManager entityManager) {
        entityManager.persist(installments);
    }

    public void delete(Installments installments) {
        EntityManager entityManager = ConfigJpa.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Installments installmentsMerge = entityManager.merge(installments);
            entityManager.remove(installmentsMerge);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public List<Installments> getByStudentLoan(StudentLoan studentLoan) {
        List<Installments> installments = new ArrayList<>();
        EntityManager entityManager = ConfigJpa.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("from Installments i where i.studentLoan=:studentLoan ");
            query.setParameter("studentLoan", studentLoan);
            installments = query.getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return installments;
    }

    public void update(Installments installments) {
        EntityManager entityManager = ConfigJpa.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(installments);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public Installments getById(int id) {
        EntityManager entityManager = ConfigJpa.getInstance().createEntityManager();
        Installments installments = null;
        try {
            entityManager.getTransaction().begin();
            installments = entityManager.find(Installments.class, id);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return installments;
    }
}
