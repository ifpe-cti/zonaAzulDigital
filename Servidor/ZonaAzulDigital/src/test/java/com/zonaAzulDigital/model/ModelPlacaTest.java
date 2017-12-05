/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.model;

import com.zonaAzulDigital.Excecao.PlacaException;
import com.zonaAzulDigital.entidades.Placa;
import javax.enterprise.inject.Model;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class ModelPlacaTest {
    
    public ModelPlacaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of recuperar method, of class ModelPlaca.
     */
    Placa placa1;
    Placa placa2;
    Placa placa3;
    Placa placa4;
    Placa placa5;

    @Test(expected = PlacaException.class)
    public void testValidarPlacaNull(){
        placa1 = null;       
        ModelPlaca.validar(placa1);      
    }

    @Test(expected = PlacaException.class)
    public void testValidarPlacaLetraNull(){     
        placa2.setLetras(null);
        ModelPlaca.validar(placa2);      
    }
    
    @Test(expected = PlacaException.class)
    public void testValidarPlacaNumeroNull(){
        placa2.setNumeros(null);
        ModelPlaca.validar(placa2);      
    }
    
    @Test(expected = PlacaException.class)
    public void testValidarPlacaDeveTer3Letras(){
        placa2.setNumeros(null);
        ModelPlaca.validar(placa2);      
    }
    
    @Test(expected = PlacaException.class)
    public void testValidarPlacaDeveTer4numeros(){
        placa2.setNumeros("12345");
        ModelPlaca.validar(placa2);      
    }
    
    
    
    
}
