/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.tests;

import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.Excecao.PlacaException;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.interfaces.DAOPlaca;
import com.zonaAzulDigital.interfaces.ModelPlacaInterface;
import com.zonaAzulDigital.model.ModelPlaca;
import com.zonaAzulDigital.tests.DAO.DAOPlacaFake;
import com.zonaAzulDigital.tests.DAO.base.Placas;
import junit.framework.Assert;
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
        mp = new ModelPlaca(new DAOPlacaFake(new Placas()));
        p1 = new Placa("KHX", "0069");
    }

    @Test
    public void deveRecuperarPlaca() throws DaoException {
        mp.recuperar(new Placa("KHX", "0069"));
    }

    @Test
    public void deveLevantarExcecaoAoBuscarPlaca() throws DaoException, PlacaException {
        excecao.expect(PlacaException.class);
        Placa p = mp.recuperar(new Placa("KHP", "0069"));

        if (p == null) {
            throw new PlacaException(PlacaException.NULL);
        }
    }

    @Test
    public void deveLevantarExcecaoNoMetodoValidarPassandoPlacaNull() throws PlacaException {
        excecao.expect(PlacaException.class);
        excecao.expectMessage(PlacaException.NULL);
        mp.validar(null);
    }

    @Test
    public void deveLevantarExcecaoNoMetodoValidarPassandoPlacaComLetrasMenorQue3() throws PlacaException {
        excecao.expect(PlacaException.class);
        excecao.expectMessage(PlacaException.LETRAS);
        mp.validar(new Placa("aa", "1234"));
    }

    @Test
    public void deveLevantarExcecaoNoMetodoValidarPassandoPlacaComNumerosMenorQue4() throws PlacaException {
        excecao.expect(PlacaException.class);
        excecao.expectMessage(PlacaException.NUMEROS);
        mp.validar(new Placa("aaa", "123"));
    }

    @Test
    public void deveLevantarExcecaoNoMetodoValidarPassandoPlacaComNumerosComLetras() throws PlacaException {
        excecao.expect(PlacaException.class);
        excecao.expectMessage(PlacaException.NUMEROS);
        mp.validar(new Placa("aaa", "123a"));
    }

    @Test
    public void deveLevantarExcecaoNoMetodoValidarPassandoPlacaComLetrasComNumeros() throws PlacaException {
        excecao.expect(PlacaException.class);
        excecao.expectMessage(PlacaException.LETRAS);
        mp.validar(new Placa("aa1", "1234"));
    }

    @Test
    public void deveValidarPlaca() throws PlacaException {
        mp.validar(new Placa("aaa", "1234"));
    }
    
    @Test(expected = PlacaException.class)
    public void validarPlacaNull() throws PlacaException{
        DAOPlaca daoPlaca = null;
        ModelPlaca modelplaca = new ModelPlaca(daoPlaca);
        Placa placa = null;
        boolean resultado = modelplaca.validar(placa);
       
    }
    @Test
    public void validarPlaca() throws PlacaException{
        DAOPlaca daoPlaca = null;
        ModelPlaca modelplaca = new ModelPlaca(daoPlaca);
        Placa placa = new Placa(1,"ASD","1234");
        boolean resultado = modelplaca.validar(placa);
        Assert.assertTrue(true);
    }
    @Test
    public void validarPlacaLetras() throws PlacaException{
        Placa placa = new Placa(1,"ASD","1234");
        int resultado = placa.getLetras().length();
        Assert.assertEquals(3, resultado);
    }
    
    @Test (expected = PlacaException.class)
    public void validarPlacaLetrasExcecao() throws PlacaException{
        DAOPlaca daoPlaca = null;
        ModelPlaca modelplaca = new ModelPlaca(daoPlaca);
        Placa placa = new Placa(1,"ASDF","1234");
        boolean resultado = modelplaca.validar(placa);
    }
    @Test
    public void validarPlacaNumeros() throws PlacaException{
        Placa placa = new Placa(1,"ASD","1234");
        int resultado = placa.getNumeros().length();
        Assert.assertEquals(4, resultado);
    }
    
    @Test (expected = PlacaException.class)
    public void validarPlacaNumerosExcecao() throws PlacaException{
        DAOPlaca daoPlaca = null;
        ModelPlaca modelplaca = new ModelPlaca(daoPlaca);
        Placa placa = new Placa(1,"ASD","12345");
        boolean resultado = modelplaca.validar(placa);
    }
    @Test (expected = PlacaException.class)
    public void validarLetrasExcecao()throws PlacaException{
        DAOPlaca daoPlaca = null;
        ModelPlaca modelplaca = new ModelPlaca(daoPlaca);
        Placa placa = new Placa(1,"ASD4","1234");
        for (int i = 0; i < placa.getLetras().length(); i++) {
        char c = placa.getLetras().charAt(i);
        if ( c < 'A' || c > 'Z') {
             modelplaca.validar(placa);
        }
        }
    }
    @Test
    public void validarLetras()throws PlacaException{
        DAOPlaca daoPlaca = null;
        ModelPlaca modelplaca = new ModelPlaca(daoPlaca);
        Placa placa = new Placa(1,"ASD","1234");
        for (int i = 0; i < placa.getLetras().length(); i++) {
        char c = placa.getLetras().charAt(i);
        if ( c < 'A' || c > 'Z') {
            boolean resultado = modelplaca.validar(placa);
        }
        }
        Assert.assertTrue(true);
    }
    @Test (expected = PlacaException.class)
    public void validarNumerosExcecao()throws PlacaException{
        DAOPlaca daoPlaca = null;
        ModelPlaca modelplaca = new ModelPlaca(daoPlaca);
        Placa placa = new Placa(1,"ASD","1234A");
        for (int i = 0; i < placa.getNumeros().length(); i++) {
       char num = placa.getNumeros().charAt(i);
            if(num < '0' || num > '9'){
             modelplaca.validar(placa);
        }
        }
    }
    @Test
    public void validarNumeros()throws PlacaException{
        DAOPlaca daoPlaca = null;
        ModelPlaca modelplaca = new ModelPlaca(daoPlaca);
        Placa placa = new Placa(1,"ASD","1234");
        for (int i = 0; i < placa.getNumeros().length(); i++) {
        char num = placa.getNumeros().charAt(i);
            if(num < '0' || num > '9'){
            boolean resultado = modelplaca.validar(placa);
        }
        }
        Assert.assertTrue(true);
    }
}
