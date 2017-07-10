/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.tests;

import com.zonaAzulDigital.DAO.DaoPlacaBD;
import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.Excecao.PlacaException;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.interfaces.DAOPlaca;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Carlos Eduardo
 */
public class DaoPlacaTeste {
    
    DAOPlaca dpbd;
    Placa p, p1;
      
    @Rule
    public ExpectedException excecao = ExpectedException.none();
    
/*    @Before
    public void before(){
         dpbd = new DaoPlacaBD();
         p = new Placa("kkz", "1234");
    }
    
    @Test
    public void testeRecuperarPlacaId() throws DaoException{
        
        dpbd.cadastrar(p);
        
        Placa placa = dpbd.recuperarPorId(3);
        
        assertEquals("KKZ-1234",placa.toString());
         
    }
    
    @Test
    public void testeRecuperarPlaca() throws DaoException{
        
        p1 = new Placa("rgb", "1234");
        
        dpbd.cadastrar(p1);
        
        Placa placa = dpbd.recuperar(p1.getLetras(), p1.getNumeros());
        
        assertEquals("RGB-1234", placa.toString());
        
    }
    
    @Test
    public void disparaExcecaoRecuperarPlacaLetras() throws DaoException{
        excecao.expect(DaoException.class);
        excecao.expectMessage(PlacaException.NAOENCONTRADA);
        
        p1 = new Placa("rbg", "1243");
        
        Placa placa = dpbd.cadastrar(p1);
        
        dpbd.recuperar("rth", placa.getNumeros());
    }
    
    @Test
    public void disparaExcecaoRecuperarPlacaNumeros() throws DaoException{
        excecao.expect(DaoException.class);
        excecao.expectMessage(PlacaException.NAOENCONTRADA);
        
        p1 = new Placa("rbg", "1243");
        
        Placa placa = dpbd.cadastrar(p1);
        
        dpbd.recuperar(placa.getLetras(), "5546");
    }
    
    @Test
    public void testeCadastroPlaca() throws DaoException{
        p1 = new Placa("kkz", "2443");
        
        Placa placa = dpbd.cadastrar(p1);
        
        assertEquals("KKZ-2443", placa.toString());
    }*/
    
}
