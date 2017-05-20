/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.model.DAO;

import Hibernate.HibernateUtil;
import com.zonaAzulDigital.entidades.Placa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author JonasJr
 */
public class Placas implements DAO<Placa>{

    @Override
    public Placa cadastrar(Placa placa) {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        em.getTransaction().begin();
        em.persist(em);
        em.getTransaction().commit();
        return placa;
    }

    @Override
    public Placa atualizar(Placa placa) {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        em.getTransaction().begin();
        em.merge(em);
        em.getTransaction().commit();
        return placa;
    }

    @Override
    public Placa recuperarPorId(int id) {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        Placa placa = em.find(Placa.class, id);
        return placa;
    }

    @Override
    public Placa deletar(Placa placa) {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        em.getTransaction().begin();
        em.remove(placa);
        em.getTransaction().commit();
        return placa;
    }

    @Override
    public List<Placa> listarTudo() {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
     
        String hql = "FROM Placas";
        Query query = em.createQuery(hql);
        return  query.getResultList();
        
    }
    
}
