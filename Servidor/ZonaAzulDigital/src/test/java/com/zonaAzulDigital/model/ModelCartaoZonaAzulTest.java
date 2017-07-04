/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.model;

import com.zonaAzulDigital.DAO.DaoCompraCartaoZADB;
import com.zonaAzulDigital.entidades.CartaoZonaAzul;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.interfaces.ModelCartaoZonaAzulInterface;
import com.zonaAzulDigital.tests.DAO.DAOCartaoZonaAzulFake;
import com.zonaAzulDigital.tests.DAO.DAOCompraCartaoZAFake;
import com.zonaAzulDigital.tests.DAO.DAOMotoristaFake;
import com.zonaAzulDigital.tests.DAO.DAOPlacaFake;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author JonasJr
 */
public class ModelCartaoZonaAzulTest {
    
    private ModelCartaoZonaAzulInterface modelCarttaoZA;
    
    public ModelCartaoZonaAzulTest() {
        this.modelCarttaoZA = new ModelCartaoZonaAzul(new DAOMotoristaFake(), new DAOCartaoZonaAzulFake(),
                new DAOCompraCartaoZAFake(), new DAOPlacaFake());
    }
    
    @BeforeClass
    public static void configAmbienteTeste() {
    }
  
    @Ignore
    @Test
    public void testComprar() throws Exception {
        System.out.println("comprar");
        Motorista motorista = null;
        Placa placa = null;
        ModelCartaoZonaAzul instance = null;
        CartaoZonaAzul expResult = null;
        CartaoZonaAzul result = instance.comprar(motorista, placa);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    @Ignore
    @Test
    public void testRecuperar() throws Exception {
        System.out.println("recuperar");
        CartaoZonaAzul cartaoZA = null;
        ModelCartaoZonaAzul instance = null;
        CartaoZonaAzul expResult = null;
        CartaoZonaAzul result = instance.recuperar(cartaoZA);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    @Ignore
    @Test
    public void testRecuperarUltimo() throws Exception {
        System.out.println("recuperarUltimo");
        Placa placa = null;
        ModelCartaoZonaAzul instance = null;
        CartaoZonaAzul expResult = null;
        CartaoZonaAzul result = instance.recuperarUltimo(placa);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
