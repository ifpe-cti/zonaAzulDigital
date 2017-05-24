/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.model.DAO;

import Hibernate.HibernateUtil;
import com.zonaAzulDigital.entidades.Motorista;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author JonasJr
 */
public class Motoristas implements DAO<Motorista>{

    @Override
    public Motorista cadastrar(Motorista motorista) {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        em.getTransaction().begin();
        em.persist(motorista);
        em.getTransaction().commit();
        return  motorista;
    }

    @Override
    public Motorista atualizar(Motorista motorista) {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        em.getTransaction().begin();
        em.merge(motorista);
        em.getTransaction().commit();
        return  motorista;
    }

    @Override
    public Motorista recuperarPorId(int id) {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        Motorista motorista = em.find(Motorista.class, id);
        return motorista;
    }

    @Override
    public Motorista deletar(Motorista motorista) {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        em.getTransaction().begin();
        em.remove(motorista);
        em.getTransaction().commit();
        return  motorista;
    }

    @Override
    public List<Motorista> listarTudo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
