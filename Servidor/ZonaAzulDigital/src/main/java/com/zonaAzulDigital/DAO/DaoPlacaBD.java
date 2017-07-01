/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.DAO;

import Hibernate.HibernateUtil;
import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.Excecao.PlacaException;
import com.zonaAzulDigital.Excecao.PlacaInvalidaException;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.interfaces.DAOPlaca;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author JonasJr
 */
public class DaoPlacaBD implements DAOPlaca {

    @Override
    public Placa recuperarPorId(int id) {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        Placa placa = em.find(Placa.class, id);
        em.close();
        return placa;
    }

    @Override
    public Placa recuperar(String letras, String numeros) throws DaoException {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        String hql = "FROM Placa p WHERE p.letras = :p1 and p.numeros = :p2 ";
        Query query = em.createQuery(hql);

        query = query.setParameter("p1", letras);
        query = query.setParameter("p2", numeros);
        Placa placa = new Placa();
        try {
            placa = (Placa) query.getSingleResult();
        } catch (Exception e) {
            throw new DaoException(PlacaException.NAOENCONTRADA.msg);
        } finally {
            em.close();
        }
        return placa;
    }

    @Override
    public List<Placa> listarTudo() {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();

        String hql = "FROM Placa";
        Query query = em.createQuery(hql);
        List<Placa> listaPlacas = query.getResultList();

        em.close();
        return listaPlacas;

    }

    @Override
    public Placa cadastrar(Placa placa) throws DaoException {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(placa);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DaoException(DaoException.NAOCADASTRADO);
        } finally {
            em.close();
        }
        return placa;
    }

}
