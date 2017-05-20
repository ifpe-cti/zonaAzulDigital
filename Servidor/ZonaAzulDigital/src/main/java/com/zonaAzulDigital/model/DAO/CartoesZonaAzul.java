/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.model.DAO;

import Hibernate.HibernateUtil;
import com.zonaAzulDigital.entidades.CartaoZonaAzul;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author JonasJr
 */
public class CartoesZonaAzul implements DAO{

    @Override
    public Object cadastrar(Object cartao) {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        em.getTransaction().begin();
        em.persist(cartao);
        em.getTransaction().commit();
        return cartao;
    }

    @Override
    public Object atualizar(Object cartao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object recuperarPorId(int id) {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        CartaoZonaAzul cartao = em.find(CartaoZonaAzul.class, id);
        return cartao;
    }

    @Override
    public Object deletar(Object cartao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List listarTudo() {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        String hql = "FROM CartaoZonaAzul";
        Query query = em.createQuery(hql);
        
        return query.getResultList();
    }
    
}
