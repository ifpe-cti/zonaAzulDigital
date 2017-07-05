/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.DAO;

import Hibernate.HibernateUtil;
import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.entidades.CartaoZonaAzul;
import com.zonaAzulDigital.entidades.CartaoZonaAzulInfo;
import com.zonaAzulDigital.entidades.CompraCartaoZA;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.interfaces.DAOCartaoZonaAzul;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author JonasJr
 */
public class DaoCartaoZonaAzulBD implements DAOCartaoZonaAzul {

//    @Override
//    public CartaoZonaAzul comprar(CartaoZonaAzul cartaoZonaAzul, Motorista motorista) throws DaoException {
//        EntityManager em = HibernateUtil.getInstance().getEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.persist(cartaoZonaAzul);
//            em.merge(motorista);
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            em.getTransaction().rollback();
//            throw new DaoException(DaoException.NAOCADASTRADO);
//        } finally {
//            em.close();
//        }
//        return cartaoZonaAzul;
//    }
    @Override
    public CartaoZonaAzul recuperarUltimo(Placa placa) {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();

        String hql = "FROM CartaoZonaAzul c WHERE c.placa = :p1 ";
        Query query = em.createQuery(hql);
        query = query.setParameter("p1", placa);
        List<CartaoZonaAzul> cartoes = query.getResultList();
        CartaoZonaAzul cartaoZonaAzul = (CartaoZonaAzul) cartoes.get(cartoes.size() - 1);

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

    @Override
    public BigDecimal preco(String cidade) throws DaoException {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        BigDecimal preco;
        try {
            String hql = "FROM CartaoZonaAzulInfo c WHERE c.cidade = :p1";
            Query query = em.createQuery(hql);
            query = query.setParameter("p1", cidade);
            preco = ((CartaoZonaAzulInfo) query.getSingleResult()).getPreco();
        } catch (NumberFormatException ex) {
            throw new DaoException(ex.getMessage());
        }
        return preco;
    }

    @Override
    public CartaoZonaAzulInfo cadastrar(CartaoZonaAzulInfo cartaoZonaAzulInfo) throws DaoException {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(cartaoZonaAzulInfo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new DaoException(DaoException.NAOCADASTRADO);
        }
        return cartaoZonaAzulInfo;
    }

    @Override
    public List<CartaoZonaAzul> listarCartoesAtivos(Motorista m) {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        String hql = "SELECT ca FROM  CompraCartaoZA co, CartaoZonaAzul ca WHERE co.motorista.id = :p1 and "
                + "co.cartaoZonaAzul.numero = ca.numero and ca.dataFim > :p2";
        Query query = em.createQuery(hql);
        query.setParameter("p1", m.getId());
        query.setParameter("p2", new Date(System.currentTimeMillis()));
        List<CartaoZonaAzul> cartoesComprados = query.getResultList();

        return cartoesComprados;
    }

}
