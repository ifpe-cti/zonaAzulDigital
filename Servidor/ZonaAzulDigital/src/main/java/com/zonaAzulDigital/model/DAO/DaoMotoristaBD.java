/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.model.DAO;

import Hibernate.HibernateUtil;
import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.Excecao.MotoristaException;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.model.DAO.interfaces.DAOMotorista;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author JonasJr
 */
public class DaoMotoristaBD implements DAOMotorista {

    @Override
    public Motorista cadastrar(Motorista motorista) throws DaoException {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(motorista);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DaoException(DaoException.NAOCADASTRADO);
        } finally {
            em.close();
        }
        return motorista;
    }

    @Override
    public Motorista atualizar(Motorista motorista) throws DaoException {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(motorista);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DaoException(DaoException.NAOATUALIZADO);
        } finally {
            em.close();
        }
        return motorista;
    }

    @Override
    public Motorista recuperarPorId(int id) {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        Motorista motorista = em.find(Motorista.class, id);
        return motorista;
    }

    public Motorista recuperar(String cpf) throws DaoException {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        String hql = "FROM Motorista m WHERE m.cpf = :p1 ";
        Query query = em.createQuery(hql);

        query = query.setParameter("p1", cpf);
        Motorista motorista = new Motorista();
        
        try {
            motorista = (Motorista) query.getSingleResult();
        } catch (Exception e) {
            throw new DaoException(MotoristaException.NAOENCONTRADA.msg);
        } finally {
            em.close();
        }
        return motorista;
    }

    
    @Override
    public List<Motorista> listarTudo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
