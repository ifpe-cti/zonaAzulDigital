/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.tests;

import com.zonaAzulDigital.DAO.DaoMotoristaBD;
import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.Excecao.LoginException;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.interfaces.DAOMotorista;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Carlos Eduardo
 */
public class LoginMotoristaTest {
    
    public DAOMotorista dmbd;
    
    public Motorista m;
    
    
    @Rule
    public ExpectedException excecao = ExpectedException.none();
    
    @Before
    public void before(){
        dmbd = new DaoMotoristaBD();
     
    }
    @Ignore
    @Test
    public void testeDeLogin() throws LoginException, DaoException{
        
        m = new Motorista(0, "Eduardo", "11792558402", BigDecimal.ZERO, "edu");
        
        Motorista motorista = dmbd.cadastrar(m);
        
        Motorista motorista1 = dmbd.login(motorista.getCpf(), motorista.getSenha());
        
        assertEquals("Eduardo", motorista1.getNome());
    }
    
    @Ignore
    @Test
    public void disparaExcecaoDeLoginCpfErrado() throws  DaoException, LoginException{
        excecao.expect(LoginException.class);
        excecao.expectMessage(LoginException.FALHOU);
        m = new Motorista(0, "Evandro", "11793558402", BigDecimal.ZERO, "eva");
        
        Motorista motorista = dmbd.cadastrar(m);
        
        dmbd.login("12312312312", motorista.getSenha());
    }
    @Ignore
    @Test
    public void disparaExcecaoDeLoginSenhaErrado() throws DaoException, LoginException{
        excecao.expect(LoginException.class);
        excecao.expectMessage(LoginException.FALHOU);
        
        m = new Motorista(0, "Izaquias", "11793658402", BigDecimal.ZERO, "eva");
        
        Motorista motorista = dmbd.cadastrar(m);
        
        dmbd.login(motorista.getCpf(), "asd");
    }
}
