package dao;

import dao.interfases.Repository;
import model.entity.CreditCard;

import javax.persistence.EntityManager;

public class CreditCardRepository implements Repository<CreditCard> {
    private static CreditCardRepository creditCardRepository = new CreditCardRepository();

    private CreditCardRepository() {
    }

    public static CreditCardRepository getInstance() {
        return creditCardRepository;
    }
    @Override
    public void save(CreditCard creditCard) {

        EntityManager entityManager = ConfigJpa.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(creditCard);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void update(CreditCard creditCard) {
        EntityManager entityManager = ConfigJpa.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(creditCard);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void delete(CreditCard creditCard) {
        EntityManager entityManager = ConfigJpa.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            CreditCard creditCardPersist = entityManager.merge(creditCard);
            entityManager.remove(creditCardPersist);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public CreditCard getById(int id) {
        CreditCard creditCard = null;
        EntityManager entityManager = ConfigJpa.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            creditCard = entityManager.find(CreditCard.class, id);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return creditCard;
    }
}
