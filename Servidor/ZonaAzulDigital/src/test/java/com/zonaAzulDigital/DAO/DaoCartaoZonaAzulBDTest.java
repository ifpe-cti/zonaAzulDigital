/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.DAO;

import Hibernate.HibernateUtil;
import com.zonaAzulDigital.entidades.CartaoZonaAzul;
import com.zonaAzulDigital.entidades.CartaoZonaAzulInfo;
import com.zonaAzulDigital.entidades.CompraCartaoZA;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.entidades.Placa;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JonasJr
 */
public class DaoCartaoZonaAzulBDTest {

    public DaoCartaoZonaAzulBDTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @Before
    public void setUp() {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        String hql1 = "delete from " + CompraCartaoZA.class.getName();
        String hql2 = "delete from " + Placa.class.getName();
        String hql3 = "delete from " + CartaoZonaAzul.class.getName();
        String hql4 = "delete from " + Motorista.class.getName();
        String hql5 = "delete from " + CompraCartaoZA.class.getName();
        String hql6 = "delete from " + CompraCartaoZA.class.getName();
        try {
            em.getTransaction().begin();
            em.createQuery(hql1).executeUpdate();
            em.getTransaction().commit();
            em.flush();

        } catch (Exception e) {

        } finally {
            em.close();
        }
    }

}
