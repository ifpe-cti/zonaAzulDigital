/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.Criptografia;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author corona
 */
public class MD5CripTest {
    
    MD5Crip cripto;
    MD5Crip cripto2;
    String senha = "batata";
    
    public MD5CripTest() {
    }

    @Test
    public void senhaSringNormal() {
         cripto = new MD5Crip();
         String atual = cripto.encriptar(senha);
         assertEquals("9eb71ab7420eb452a22787ca4fab501b",atual);
        
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void senhaNula(){
        cripto2 = new MD5Crip();
        cripto2.encriptar(null);
        
        
    }
    
    
}