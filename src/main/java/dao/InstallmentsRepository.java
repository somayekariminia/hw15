package dao;

import model.entity.Installments;

import javax.persistence.EntityManager;

public class InstallmentsRepository {
    private static InstallmentsRepository installmentsRepository = new InstallmentsRepository();

    private InstallmentsRepository() {
    }

    public static InstallmentsRepository getInstance() {
        return installmentsRepository;
    }

    public void save(Installments installments) {
        EntityManager entityManager = ConfigJpa.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(installments);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
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
}
