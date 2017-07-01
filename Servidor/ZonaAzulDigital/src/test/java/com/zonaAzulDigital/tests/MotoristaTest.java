/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.tests;

import com.zonaAzulDigital.Excecao.CpfException;
import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.Excecao.MotoristaException;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.interfaces.DAOMotorista;
import com.zonaAzulDigital.interfaces.ModelMotoristaInterface;
import com.zonaAzulDigital.model.ModelMotorista;
import com.zonaAzulDigital.tests.DAO.DAOMotoristaFake;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Samuel
 */

public class MotoristaTest {
    
    public ModelMotoristaInterface md;
    
    public Motorista m;
    public Motorista m1,m2,m3;
    
    @Rule
    public ExpectedException excecao = ExpectedException.none();
    
    @Before
    public void before(){
        md = new ModelMotorista(new DAOMotoristaFake());
        m1 = new Motorista(0, "Samuel", "11791558402", BigDecimal.ZERO, "samuel");
        m2 = new Motorista(0, "Carlos Eduardo", "04982857407", BigDecimal.ZERO, "cadu");
        m3 = new Motorista(0, "Jonas", "10654901430", BigDecimal.ZERO, "xonas");
    }
    
    
    @Test
    public void testaCadastroSimplesdeMotorista() throws DaoException, CpfException, MotoristaException {
        m = new Motorista(1, "Samuel", "11791558402", BigDecimal.ZERO, "alabala");
        Motorista motorista = md.cadastrar(m);
        assertEquals("Samuel",motorista.getNome());
    }
    
    @Test
    public void deveDispararExcecaoDeCPFVazio() throws CpfException, DaoException, MotoristaException{
        excecao.expect(CpfException.class);
        m = new Motorista(1, "Samuel", "", BigDecimal.ZERO, "alabala");
        md.cadastrar(m);
    }
    
    @Test
    public void deveDispararExcecaoDeCPFNull() throws CpfException, DaoException, MotoristaException{
        excecao.expect(CpfException.class);
        m = new Motorista(1, "Samuel", null, BigDecimal.ZERO, "alabala");
        md.cadastrar(m);
    }
    
    @Test
    public void deveDispararExcecaoDeMotoristaVazio() throws DaoException, CpfException, MotoristaException{
        excecao.expect(MotoristaException.class);
        m = new Motorista();
        md.cadastrar(m);
    }
    
    @Test
    public void deveDispararExcecaoDeMotoristaNull() throws DaoException, CpfException, MotoristaException{
        excecao.expect(MotoristaException.class);
        m = null;
        md.cadastrar(m);
    }
    
    @Test
    public void deveDispararExcecaoDeMotoristaComNomeVazio() throws DaoException, CpfException, MotoristaException{
        excecao.expect(MotoristaException.class);
        
        md.cadastrar(new Motorista(1, "", "11791558402", BigDecimal.ZERO, "samuel"));
    }
    
    @Test
    public void deveDispararExcecaoDeMotoristaComNomeNull() throws DaoException, CpfException, MotoristaException{
        excecao.expect(MotoristaException.class);
        
        md.cadastrar(new Motorista(1, null, "11791558402", BigDecimal.ZERO, "samuel"));
    }
    
    
    @Test
    public void deveDispararExcecaoDeMotoristaComSenhaVazia() throws DaoException, CpfException, MotoristaException{
        excecao.expect(MotoristaException.class);
        
        md.cadastrar(new Motorista(1, "Samuel", "11791558402", BigDecimal.ZERO, ""));
    }
    
    @Test
    public void deveDispararExcecaoDeMotoristaComSenhaNull() throws DaoException, CpfException, MotoristaException{
        excecao.expect(MotoristaException.class);
        
        md.cadastrar(new Motorista(1, "Samuel", "11791558402", BigDecimal.ZERO, null));
    }
    @Test
    public void deveDispararExcecaoDeMotoristaComCreditoNull() throws DaoException, CpfException, MotoristaException{
        excecao.expect(MotoristaException.class);
        
        md.cadastrar(new Motorista(1, "Samuel", "11791558402", null, "Samuel"));
    }
    @Test
    public void deveDispararExcecaoDeMotoristaComCreditoNegativo() throws DaoException, CpfException, MotoristaException{
        excecao.expect(MotoristaException.class);
        
        md.cadastrar(new Motorista(1, "Samuel", "11791558402", new BigDecimal(-1), "Samuel"));
    }
    
    @Test
    public void deveRecuperarMotoristaPorCpf() throws DaoException, CpfException, MotoristaException{
        md.cadastrar(m1);
        md.cadastrar(m2);
        md.cadastrar(m3);
        
        Motorista motoristaRecuperado = md.recuperar(m1);
        
        assertEquals("Samuel",motoristaRecuperado.getNome());
    }
}
