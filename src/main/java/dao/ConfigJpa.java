package dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConfigJpa {
    private static EntityManagerFactory emf= Persistence.createEntityManagerFactory("PU");
    public static EntityManagerFactory getInstance(){
        return emf;
    }
    private ConfigJpa() {
    }
}
