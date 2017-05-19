package Hibernate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JonasJr
 */
public class HibernateUtil {
    
    private static HibernateUtil myself;
    private EntityManagerFactory factory = null;
    
    private HibernateUtil(){
        factory = Persistence.createEntityManagerFactory("ZonaAzulDigitalUP");
    }

    public EntityManagerFactory getFactory() {
        return factory;
    }
    
    public EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    public static HibernateUtil getInstance() {
        if (myself == null) {
            myself = new HibernateUtil();
        }

        return myself;
    }
}
