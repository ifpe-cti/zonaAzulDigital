/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.tests;


import com.zonaAzulDigital.DAO.DaoCompraCartaoZADB;
import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.entidades.CartaoZonaAzul;
import com.zonaAzulDigital.entidades.CompraCartaoZA;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.entidades.Placa;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Samuel
 */
public class DAOCompraCartaoTest {
    DaoCompraCartaoZADB dcza;
    
    @Rule
    public ExpectedException excecao = ExpectedException.none();
    
    @Before
    public void before(){
        dcza = new DaoCompraCartaoZADB();
    }
    
    @Test
    public void deveEfetuarCompra() throws DaoException{
        Motorista m = new Motorista(0, "Samuel", "11791558402", BigDecimal.ZERO, "samuel");
        CartaoZonaAzul cza = new CartaoZonaAzul(new Placa("AAA", "1234"));
        CompraCartaoZA ccz = new CompraCartaoZA(m, cza);
        
        CompraCartaoZA compraRetorno= dcza.comprar(ccz);
        
        assertEquals(m.getCpf(),compraRetorno.getMotorista().getCpf());
    }
    
    @Test
    public void deveLevantarExcecaoDeCompra() throws DaoException{
        excecao.expect(DaoException.class);
        dcza.comprar(null);
    }
    
    
    
}
