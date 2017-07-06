/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.tests;

import com.zonaAzulDigital.DAO.DaoPlacaBD;
import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.interfaces.DAOPlaca;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Carlos Eduardo
 */
public class DaoPlacaTeste {
    
    DAOPlaca dpbd;
    Placa p;
    
    
    @Before
    public void before(){
         dpbd = new DaoPlacaBD();
         p = new Placa("kkz", "1234");
    }
    
    @Test
    public void testeRecuperarPlacaId() throws DaoException{
        
        dpbd.cadastrar(p);
        
        Placa p1 = dpbd.recuperarPorId(1);
        
         assertEquals("KKZ-1234",p1.toString());
         
    }
    
    
    
}
