/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.tests;

import com.zonaAzulDigital.Excecao.CpfException;
import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.interfaces.DAOMotorista;
import com.zonaAzulDigital.interfaces.ModelMotoristaInterface;
import com.zonaAzulDigital.model.ModelMotorista;
import com.zonaAzulDigital.tests.DAO.DAOFakeMotorista;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Samuel
 */

public class MotoristaTest {
    
    private ModelMotoristaInterface md;
    
    @Before
    public void before(){
        md = new ModelMotorista(new DAOFakeMotorista());
    }
    
    
    @Test
    public void testaCadastroSimplesdeMotorista() throws DaoException, CpfException {
       Motorista m = new Motorista();
       
       m.setNome("Samuel");
       m.setCpf("11791558402");
       m.setCredito(BigDecimal.ZERO);
       m.setId(1);
       m.setSenha("alabala");
       
       Motorista motorista = md.cadastrar(m);
       assertEquals("Samuel",motorista.getNome());
    }
    
}
