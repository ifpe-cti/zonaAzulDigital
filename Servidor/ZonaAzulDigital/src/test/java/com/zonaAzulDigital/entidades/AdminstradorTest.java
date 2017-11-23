/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.entidades;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniel Carneiro Rosa
 */
public class AdminstradorTest {
    
    @Test
    public void testGetPrimeiroNome_NomeComEspaco() {
    
        Adminstrador admin = new Adminstrador();

        admin.setNome("Daniel Carneiro Rosa");

        String pNome = admin.getPrimeiroNome();
        
        assertEquals(pNome, "Daniel");
        
    }
    
    @Test
    public void testGetPrimeiroNome_NomeSemEspaco() {
    
        Adminstrador admin = new Adminstrador();

        admin.setNome("Adonis");

        String pNome = admin.getPrimeiroNome();
        
        assertEquals(pNome, "Adonis");
        
    }
    
    @Test
    public void testGetPrimeiroNome_NomeNull() {
        
        Adminstrador admin = new Adminstrador();

        admin.setNome(null);
        
        String pNome = admin.getPrimeiroNome();
        
        assertEquals(pNome, "");
        
    }
    
    @Test
    public void testGetPrimeiroNome_NomeCompostoApenasPorEspacos() {
       Adminstrador admin = new Adminstrador();
       
       admin.setNome("   ");
       
       String pNome = admin.getPrimeiroNome();
       
       assertEquals(pNome, "   ");
    }
    
    @Test
    public void testGetPrimeiroNome_NomeIsEmpty() {
       Adminstrador admin = new Adminstrador();
       
       admin.setNome("");
       
       String pNome = admin.getPrimeiroNome();
       
       assertEquals(pNome, "");
    }
    
}
