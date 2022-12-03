package dao;

import dao.interfases.Repository;
import model.entity.Person;
import model.entity.Student;

import javax.persistence.EntityManager;

public abstract class PersonRepository<T extends Person> implements Repository<T> {

    @Override
    public void save(T t) {
        EntityManager entityManager = ConfigJpa.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(t);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void update(T t) {
        EntityManager entityManager = ConfigJpa.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(t);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void delete(T t) {
        EntityManager entityManager = ConfigJpa.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            T t1 = entityManager.merge(t);
            entityManager.persist(t1);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public T getById(int id) {
        Person student=null;
        EntityManager entityManager = ConfigJpa.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            student = entityManager.find(Person.class, id);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return (T)student ;
    }
    public  T getByNationalCode(String nationalCode){
        Person person = null;
        EntityManager entityManager = ConfigJpa.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
           person = (Person) entityManager.createQuery("from Person p where p.nationalCode=:nationalCode")
                    .setParameter("nationalCode", nationalCode).getSingleResult();
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return (T) person;
    }
}
