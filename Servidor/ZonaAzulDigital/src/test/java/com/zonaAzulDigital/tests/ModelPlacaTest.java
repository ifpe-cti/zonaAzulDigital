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
    public void deveRecuperarPlaca() throws DaoException {
        mp.recuperar(new Placa("KHX", "0069"));
    }
    
    @Test
    public void deveLevantarExcecaoAoBuscarPlaca() throws DaoException, PlacaException{
        excecao.expect(PlacaException.class);
        Placa p = mp.recuperar(new Placa("KHP", "0069"));
        
        if ( p == null ){
            throw new PlacaException(PlacaException.NULL);
        }
    }
    
    
    @Test
    public void deveLevantarExcecaoNoMetodoValidarPassandoPlacaNull() throws PlacaException{
        excecao.expect(PlacaException.class);
        excecao.expectMessage(PlacaException.NULL);
        mp.validar(null);
    }
    
    
    @Test
    public void deveLevantarExcecaoNoMetodoValidarPassandoPlacaComLetrasMenorQue3() throws PlacaException{
        excecao.expect(PlacaException.class);
        excecao.expectMessage(PlacaException.LETRAS);
        mp.validar(new Placa("aa", "1234"));
    }
    
    @Test
    public void deveLevantarExcecaoNoMetodoValidarPassandoPlacaComNumerosMenorQue4() throws PlacaException{
        excecao.expect(PlacaException.class);
        excecao.expectMessage(PlacaException.NUMEROS);
        mp.validar(new Placa("aaa", "123"));
    }
    
    @Test
    public void deveLevantarExcecaoNoMetodoValidarPassandoPlacaComNumerosComLetras() throws PlacaException{
        excecao.expect(PlacaException.class);
        excecao.expectMessage(PlacaException.NUMEROS);
        mp.validar(new Placa("aaa", "123a"));
    }
    
    @Test
    public void deveLevantarExcecaoNoMetodoValidarPassandoPlacaComLetrasComNumeros() throws PlacaException{
        excecao.expect(PlacaException.class);
        excecao.expectMessage(PlacaException.LETRAS);
        mp.validar(new Placa("aa1", "1234"));
    }
    
    @Test
    public void deveValidarPlaca() throws PlacaException{
        mp.validar(new Placa("aaa", "1234"));
    }
}
