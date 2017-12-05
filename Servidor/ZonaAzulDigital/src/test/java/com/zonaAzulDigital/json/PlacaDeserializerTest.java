/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.json;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.Excecao.PlacaException;
import com.zonaAzulDigital.Excecao.PlacaInvalidaException;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.model.ModelPlaca;
import junit.framework.Assert;
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
public class PlacaDeserializerTest {
    
    public PlacaDeserializerTest() {
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
    
    Placa p1;
    Placa p2;
    Placa p3;
    ModelPlaca x1;
    @Test(expected =  PlacaException.class)
    public void testValidarPlacaNull() throws java.lang.NullPointerException, PlacaException{
        p1 = null;
        x1.validar(p1);
        
    }
    
   // @Test(expected = MotoristaException.class)
  // public void testValidarMotoristaNull(){
   //   motorista = null;
   //     ModelMotorista.validar(motorista)
}
    
    
    
    
        
        

    

