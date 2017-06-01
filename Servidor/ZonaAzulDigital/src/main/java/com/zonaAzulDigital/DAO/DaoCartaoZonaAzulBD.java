/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.DAO;

import Hibernate.HibernateUtil;
import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.entidades.CartaoZonaAzul;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.interfaces.DAOCartaoZonaAzul;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author JonasJr
 */
public class DaoCartaoZonaAzulBD implements DAOCartaoZonaAzul {

    @Override
    public CartaoZonaAzul cadastrar(CartaoZonaAzul cartaoZonaAzul) throws DaoException {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cartaoZonaAzul);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DaoException(DaoException.NAOCADASTRADO);
        } finally {
            em.close();
        }
        return cartaoZonaAzul;
    }

    @Override
    public CartaoZonaAzul recuperarUltimo(Placa placa) {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();

        String hql = "FROM CartaoZonaAzul c WHERE c.placa = :p1 ";
        Query query = em.createQuery(hql);
        query = query.setParameter("p1", placa);
        List<CartaoZonaAzul> cartoes = query.getResultList();
        CartaoZonaAzul cartaoZonaAzul = (CartaoZonaAzul) cartoes.get(cartoes.size()-1) ;

        return cartaoZonaAzul;
    }

    @Override
    public List listarTudo() {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        String hql = "FROM CartaoZonaAzul";
        Query query = em.createQuery(hql);

        return query.getResultList();
    }


   

    @Override
    public CartaoZonaAzul recuperarPorId(int id) throws DaoException {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        CartaoZonaAzul cartaoZonaAzul = em.find(CartaoZonaAzul.class, id);
        return cartaoZonaAzul;
    }

}
