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
 * @author Samuel
 */
public class DaoMotoristaTeste {
    public DAOMotorista dmbd;
    
    public Motorista m, m1;
    
    
    @Rule
    public ExpectedException excecao = ExpectedException.none();
    
    @Before
    public void before(){
        dmbd = new DaoMotoristaBD();
        m1 = new Motorista(0, "Carlos Eduardo", "04982857407", BigDecimal.ZERO, "carlos");
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
       
         Motorista mc = dmbd.atualizar(m1);
        
        assertEquals("Carlos Eduardo", mc.getNome());
    }
}
