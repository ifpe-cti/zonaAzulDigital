/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.DAO;


import Hibernate.HibernateUtil;
import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.entidades.CompraCartaoZA;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.interfaces.DAOCompraCartaoZA;
import java.util.List;
import javax.persistence.EntityManager;

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
            em.persist(compraCartaoZA.getCartaoZonaAzul());
            em.merge(compraCartaoZA.getMotorista());
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CompraCartaoZA> recuperarPor(Motorista motorista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
