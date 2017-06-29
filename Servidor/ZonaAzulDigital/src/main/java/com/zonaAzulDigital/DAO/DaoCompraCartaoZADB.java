/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.DAO;


import Hibernate.HibernateUtil;
import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.Excecao.MotoristaException;
import com.zonaAzulDigital.entidades.CartaoZonaAzul;
import com.zonaAzulDigital.entidades.CompraCartaoZA;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.interfaces.DAOCompraCartaoZA;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author JonasJr
 */
public class DaoCompraCartaoZADB implements DAOCompraCartaoZA{

    @Override
    public CompraCartaoZA comprar(CompraCartaoZA compraCartaoZA)throws DaoException {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        try{
           
            em.getTransaction().begin();
            Motorista motorista = em.merge(compraCartaoZA.getMotorista());
            compraCartaoZA.setMotorista(motorista);
            CartaoZonaAzul cartao = em.merge(compraCartaoZA.getCartaoZonaAzul());
            compraCartaoZA.setCartaoZonaAzul(cartao);
            em.persist(compraCartaoZA);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            throw new DaoException(DaoException.NAOCADASTRADO);
        }finally{
            em.close();
        }
        return compraCartaoZA;
    }

    @Override
    public CompraCartaoZA recuperarPor(int id) {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        return em.find(CompraCartaoZA.class, id);
    }

    @Override
    public List<CompraCartaoZA> recuperarPor(Motorista motorista) throws DaoException{
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        String hql = "FROM CompraCartaoZA c WHERE c.motorista = :p1 ";
        Query query = em.createQuery(hql);

        query = query.setParameter("p1", motorista);
        List<CompraCartaoZA> lista = new ArrayList<>();
        try {
            lista = query.getResultList();
        } catch (Exception e) {
            throw new DaoException(MotoristaException.NAOENCONTRADO);
        } finally {
            em.close();
        }
        return lista;
    }
    
}
