/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.tests;

import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.Excecao.PlacaException;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.interfaces.ModelPlacaInterface;
import com.zonaAzulDigital.model.ModelPlaca;
import com.zonaAzulDigital.tests.DAO.DAOPlacaFake;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Samuel
 */
public class ModelPlacaTest {

    public ModelPlacaInterface mp;
    public Placa p1;
    
    @Rule
    public ExpectedException excecao = ExpectedException.none();
    
    @Before
    public void before() {
        mp = new ModelPlaca(new DAOPlacaFake());
        p1 = new Placa("KHX", "0069");
    }

    

    @Test
    public void deveLevantarExcecaoDePlacaComLetrasInvalidasQuandoCadastrar() throws DaoException {
        excecao.expect(PlacaException.class);
        p1.setLetras("");
        
    }
}
