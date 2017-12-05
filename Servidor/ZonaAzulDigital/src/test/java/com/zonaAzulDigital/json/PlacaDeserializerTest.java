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
    
    //Verificar se o metodo não está alterando nada
    @Test public void PlacaDeveTer3LetrasE4Numeros() {
        String numeros = "1234";
        String letras ="abc";
        Placa p = new Placa(letras, numeros);
        Placa p2 = new Placa("abc","1234");        
        Assert.assertEquals(p, p2);
    }
    
    @Test(expected = PlacaException.class)
    public void PlacaDeveTer4Numeros() throws PlacaException {
        String numeros = "12345";
        if(numeros.length() > 4);  
    }
    
    @Test(expected = PlacaException.class)
    public void PlacaDeveTer3Letras() throws PlacaException {
        String letras = "abcd";
        if(letras.length() > 3);  
    }
    
    
        
        

    
}
