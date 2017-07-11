/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.DAO;

import Hibernate.HibernateUtil;
import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.controler.controllerMotorista;
import com.zonaAzulDigital.controler.controllerRelatorio;
import com.zonaAzulDigital.entidades.CartaoZonaAzul;
import com.zonaAzulDigital.entidades.CartaoZonaAzulInfo;
import com.zonaAzulDigital.entidades.CompraCartaoZA;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.entidades.VendaMes;
import com.zonaAzulDigital.interfaces.DAOCartaoZonaAzul;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author JonasJr
 */
public class DaoCartaoZonaAzulBD implements DAOCartaoZonaAzul {

    @Override
    public CartaoZonaAzul recuperarUltimo(Placa placa) {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        String hql = "SELECT ca FROM " + CartaoZonaAzul.class.getSimpleName() + " ca, " + Placa.class.getSimpleName()
                + " p WHERE ca.placa.id = p.id and p.letras = :p1 and p.numeros = :p2 and ca.dataFim =( "
                + "SELECT MAX(c.dataFim) FROM " + CartaoZonaAzul.class.getSimpleName() + " c , "
                + Placa.class.getSimpleName() + " p WHERE c.placa.id = p.id and p.letras = :p1 and p.numeros = :p2)";
        Query query = em.createQuery(hql);
        query = query.setParameter("p1", placa.getLetras());
        query = query.setParameter("p2", placa.getNumeros());
        CartaoZonaAzul cartaoZonaAzul = (CartaoZonaAzul) query.getSingleResult();
        return cartaoZonaAzul;
    }

    @Override
    public List listarTudo() {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        String hql = "FROM " + CartaoZonaAzul.class.getSimpleName();
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
            String hql = "FROM " + CartaoZonaAzulInfo.class.getSimpleName() + " c WHERE c.cidade = :p1";
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
        String hql = "SELECT ca FROM  " + CompraCartaoZA.class.getSimpleName() + " co, "
                + CartaoZonaAzul.class.getSimpleName() + " ca WHERE co.motorista.id = :p1 and "
                + "co.cartaoZonaAzul.numero = ca.numero and ca.dataFim > :p2";
        Query query = em.createQuery(hql);
        query.setParameter("p1", m.getId());
        query.setParameter("p2", new Date(System.currentTimeMillis()));
        List<CartaoZonaAzul> cartoesComprados = query.getResultList();

        return cartoesComprados;
    }

    @Override
    public List<Object[]> vendasPorMes(int ano) throws DaoException {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
//        select count(*) from cartaozonaazul as ca where year(ca.dataInicio) = '2017'
//        and month(ca.dataInicio) > '01' group by month(ca.dataInicio);
//        String hql = "FORM " + CartaoZonaAzul.class.getSimpleName() + " c WHERE c.dataInicio > :p1";
        String hql = "SELECT MONTH(c.dataInicio), COUNT(c) FROM " + CartaoZonaAzul.class.getName()
                + " c WHERE YEAR(c.dataInicio) = :p1 AND MONTH(c.dataInicio) > :p2 "
                + "GROUP BY MONTH(c.dataInicio) ORDER BY MONTH(c.dataInicio)";
        Query query = em.createQuery(hql);
        query.setParameter("p1", ano);
        query.setParameter("p2", 1);

        List<Object[]> lista = new ArrayList<>();
        try {
            lista = query.getResultList();
        } catch (Exception ex) {
            throw new DaoException(DaoException.NAOCADASTRADO);
        }
        return lista;
    }

    public CartaoZonaAzul recuperaCartaoAtivo(Placa placa) {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        String hql = "SELECT ca FROM " + CartaoZonaAzul.class.getSimpleName() + " ca, "
                + Placa.class.getSimpleName() + " p WHERE ca.placa.id = p.id and "
                + "p.letras = :p1 and p.numeros = :p2 and ca.dataFim > :p3 ";
        Query query = em.createQuery(hql);
        query.setParameter("p1", placa.getLetras());
        query.setParameter("p2", placa.getNumeros());
        Date dataInicio = new Date();
        dataInicio.setHours(dataInicio.getHours() - 2);
        query.setParameter("p3", dataInicio);

        CartaoZonaAzul cartaoAtivo = new CartaoZonaAzul();
        try {
            cartaoAtivo = (CartaoZonaAzul) query.getSingleResult();
        } catch (Exception ex) {
            Logger.getLogger(DaoCartaoZonaAzulBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cartaoAtivo;
    }

    @Override
    public List<CartaoZonaAzul> recuperarCartoesPor(Motorista motorista) {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        String hql = "SELECT ca FROM  " + CompraCartaoZA.class.getSimpleName() + " co, "
                + CartaoZonaAzul.class.getSimpleName() + " ca WHERE co.motorista.id = :p1 and "
                + "co.cartaoZonaAzul.numero = ca.numero";
        Query query = em.createQuery(hql);
        query.setParameter("p1", motorista.getId());
        List<CartaoZonaAzul> cartoes = new ArrayList<>();
        try {
            cartoes = query.getResultList();
        } catch (Exception ex) {
            Logger.getLogger(DaoCartaoZonaAzulBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cartoes;
    }
}
