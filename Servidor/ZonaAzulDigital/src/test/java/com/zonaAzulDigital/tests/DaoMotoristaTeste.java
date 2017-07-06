/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.tests;


import com.zonaAzulDigital.DAO.DaoMotoristaBD;
import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.interfaces.DAOMotorista;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Carlos Eduardo
 */
public class DaoMotoristaTeste {
    public DAOMotorista dmbd;
    
    public Motorista m, m1, m2, m3;
    
    
    @Rule
    public ExpectedException excecao = ExpectedException.none();
    
    @Before
    public void before(){
        dmbd = new DaoMotoristaBD();
        m1 = new Motorista(0, "Carlos Eduardo", "04982857407", BigDecimal.ZERO, "carlos");
        m2 =new Motorista(0, "Samuel", "11791558402", BigDecimal.ZERO, "samuel");
         m3 = new Motorista(0, "Jonas", "10654901430", BigDecimal.ZERO, "xonas");
    }
    
    @Test
    public void testandoCadastroMostorista() throws DaoException{
        Motorista mc = dmbd.cadastrar(m1);
        
         assertEquals("Carlos Eduardo",mc.getNome());
         
    }
    @Test
    public void  disparaExcecaoDao() throws DaoException{
        excecao.expect(DaoException.class);
        
         Motorista mc = new Motorista();
         dmbd.cadastrar(mc);
    }
    
    @Test
    public void testandoMotoristaAtualizado() throws DaoException{
       
       
       dmbd.cadastrar(m2);
       m2.setNome("Samuardo");
       
       Motorista mc = dmbd.atualizar(m2);
        
        assertEquals("Samuardo", mc.getNome());
    }
    
    @Test
    public void testeRecuperarPorId() throws DaoException{
       
        
       dmbd.cadastrar(m3);
       
        
        Motorista mc = dmbd.recuperarPorId(1);
        
        assertEquals("Jonas", mc.getNome());
    }
    
    public void testeRecuperarPorCpf() throws DaoException{
        Motorista mc = dmbd.recuperar( "04982857407");
        
        assertEquals("Carlos Eduardo", mc.getNome());
    }
    
}
