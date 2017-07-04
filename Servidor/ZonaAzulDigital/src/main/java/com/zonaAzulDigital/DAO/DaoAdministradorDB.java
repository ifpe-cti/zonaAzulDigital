/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.DAO;

import Hibernate.HibernateUtil;
import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.Excecao.LoginException;
import com.zonaAzulDigital.entidades.Adminstrador;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.interfaces.DaoAdminstrador;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author JonasJr
 */
public class DaoAdministradorDB implements DaoAdminstrador {

    @Override
    public Adminstrador cadastrar(Adminstrador adm) throws DaoException {
        try {
            EntityManager em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            em.persist(em);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new DaoException(DaoException.NAOCADASTRADO);
        }

        return adm;
    }

    @Override
    public Adminstrador recuperar(String cpf) throws DaoException {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        String hql = "FROM Administrador a WHERE a.cpf = :p1 ";
        Query query = em.createQuery(hql);

        query = query.setParameter("p1", cpf);
        Adminstrador adm = new Adminstrador();
        try {
            adm = (Adminstrador) query.getSingleResult();
        } catch (Exception ex) {
            throw new DaoException(DaoException.NAOCADASTRADO);
        }
        return adm;
    }

    @Override
    public Adminstrador login(String cpf, String senha) throws LoginException{
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        String hql = "FROM Administrador a WHERE a.cpf = :p1 and a.senha = :p2 ";
        Query query = em.createQuery(hql);

        query = query.setParameter("p1", cpf);
        query = query.setParameter("p2", senha);
       Adminstrador adm = new Adminstrador();

        try {
            adm = (Adminstrador) query.getSingleResult();
        } catch (Exception e) {
            throw new LoginException(LoginException.FALHOU, e);
        } finally {
            em.close();
        }
        return adm;
    }

    @Override
    public List<Adminstrador> listarTodos() {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        String hql = "FROM Administrador";
        Query query = em.createQuery(hql);

        return (List<Adminstrador>) query.getResultList();
    }

}
