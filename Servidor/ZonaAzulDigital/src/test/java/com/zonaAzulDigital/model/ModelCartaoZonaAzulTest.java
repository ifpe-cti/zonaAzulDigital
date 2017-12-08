/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.model;

import com.zonaAzulDigital.DAO.DaoPlacaBD;
import com.zonaAzulDigital.Excecao.CartaoZAException;
import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.Excecao.MotoristaException;
import com.zonaAzulDigital.Excecao.PlacaException;
import com.zonaAzulDigital.entidades.CartaoZonaAzul;
import com.zonaAzulDigital.entidades.CartaoZonaAzulInfo;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.entidades.VendaMes;
import com.zonaAzulDigital.interfaces.DAOCartaoZonaAzul;
import com.zonaAzulDigital.interfaces.DAOCompraCartaoZA;
import com.zonaAzulDigital.interfaces.DAOMotorista;
import com.zonaAzulDigital.interfaces.DAOPlaca;
import com.zonaAzulDigital.interfaces.ModelCartaoZonaAzulInterface;
import com.zonaAzulDigital.tests.DAO.DAOCartaoZonaAzulFake;
import com.zonaAzulDigital.tests.DAO.DAOCompraCartaoZAFake;
import com.zonaAzulDigital.tests.DAO.DAOMotoristaFake;
import com.zonaAzulDigital.tests.DAO.DAOPlacaFake;
import com.zonaAzulDigital.tests.DAO.base.Cartoes;
import com.zonaAzulDigital.tests.DAO.base.Compras;
import com.zonaAzulDigital.tests.DAO.base.Motoristas;
import com.zonaAzulDigital.tests.DAO.base.Placas;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author JonasJr
 */
public class ModelCartaoZonaAzulTest {

    private ModelCartaoZonaAzulInterface modelCarttaoZA;
    private DAOMotorista dAOMotorista;
    private DAOCartaoZonaAzul dAOCartaoZonaAzul;
    private DAOCompraCartaoZA dAOCompraCartaoZA;
    private DAOPlaca dAOPlaca;
    private Motorista motorista;
    private Placas placas;
    private Motoristas motoristas;
    private Cartoes cartoes;
    private Compras compras;
    
    public ModelCartaoZonaAzulTest() {
    }

    @Rule
    public ExpectedException excption = ExpectedException.none();

    @Before
    public void antesDostestes() {
        placas = new Placas();
        motoristas = new Motoristas();
        cartoes = new Cartoes(placas);
        compras = new Compras(motoristas,cartoes);
        
        dAOPlaca = new DAOPlacaFake(placas);
        dAOMotorista = new DAOMotoristaFake(motoristas);
        dAOCartaoZonaAzul = new DAOCartaoZonaAzulFake(cartoes);
        dAOCompraCartaoZA = new DAOCompraCartaoZAFake(compras);
        motorista = new Motorista("Samuel", "11791558402", "123");
    }

    @Test
    public void deveRealizarUmaCompra() throws DaoException, MotoristaException, PlacaException {
        motorista.creditar(new BigDecimal(2));
        dAOMotorista.cadastrar(motorista);
        dAOCartaoZonaAzul.cadastrar(new CartaoZonaAzulInfo("Garanhuns", new BigDecimal(2)));
        modelCarttaoZA = new ModelCartaoZonaAzul(dAOMotorista, dAOCartaoZonaAzul, dAOCompraCartaoZA, dAOPlaca);
        Placa placa = new Placa("AAA", "0066");
        CartaoZonaAzul cartao = modelCarttaoZA.comprar(motorista, placa);

        assertEquals(placa.getLetras(), cartao.getPlaca().getLetras());
        assertEquals(placa.getNumeros(), cartao.getPlaca().getNumeros());
    }

    @Test
    public void deveRealizarUmaCompraETer1CentavoDeCredito() throws DaoException, MotoristaException, PlacaException {
        motorista.creditar(new BigDecimal(2.01));
        dAOMotorista.cadastrar(motorista);
        dAOCartaoZonaAzul.cadastrar(new CartaoZonaAzulInfo("Garanhuns", new BigDecimal(2)));
        modelCarttaoZA = new ModelCartaoZonaAzul(dAOMotorista, dAOCartaoZonaAzul, dAOCompraCartaoZA, dAOPlaca);
        Placa placa = new Placa("KHX", "0066");
        CartaoZonaAzul cartao = modelCarttaoZA.comprar(motorista, placa);

        assertEquals(placa.getLetras(), cartao.getPlaca().getLetras());
        assertEquals(placa.getNumeros(), cartao.getPlaca().getNumeros());
        assertEquals(new BigDecimal(0.01).doubleValue(),
                dAOMotorista.recuperar(motorista.getCpf()).getCredito().doubleValue(), 0.0001);
    }

    @Test
    public void deveLancarExcecaoParaCompraSemCriditoSuficiente() throws DaoException, MotoristaException, PlacaException {
        excption.expect(MotoristaException.class);
        excption.expectMessage(MotoristaException.CREDITOINSUFICIENTE);
        dAOMotorista.cadastrar(motorista);
        dAOCartaoZonaAzul.cadastrar(new CartaoZonaAzulInfo("Garanhuns", new BigDecimal(2)));
        modelCarttaoZA = new ModelCartaoZonaAzul(dAOMotorista, dAOCartaoZonaAzul, dAOCompraCartaoZA, dAOPlaca);
        Placa placa = new Placa("KHX", "0066");
        CartaoZonaAzul cartao = modelCarttaoZA.comprar(motorista, placa);
    }

    @Test
    public void deveLancarExcecaoParaCompraComCredito1E99() throws DaoException, MotoristaException, PlacaException {
        excption.expect(MotoristaException.class);
        excption.expectMessage(MotoristaException.CREDITOINSUFICIENTE);
        motorista.creditar(new BigDecimal(1.99));
        dAOMotorista.cadastrar(motorista);
        dAOCartaoZonaAzul.cadastrar(new CartaoZonaAzulInfo("Garanhuns", new BigDecimal(2)));
        modelCarttaoZA = new ModelCartaoZonaAzul(dAOMotorista, dAOCartaoZonaAzul, dAOCompraCartaoZA, dAOPlaca);
        Placa placa = new Placa("KHX", "0066");
        CartaoZonaAzul cartao = modelCarttaoZA.comprar(motorista, placa);
    }

    @Test
    public void deveLancarExcecaoParaMotoristaNaoCadastrado() throws DaoException, MotoristaException, PlacaException {
        excption.expect(MotoristaException.class);
        excption.expectMessage(MotoristaException.NAOENCONTRADO);
        dAOCartaoZonaAzul.cadastrar(new CartaoZonaAzulInfo("Garanhuns", new BigDecimal(2)));
        modelCarttaoZA = new ModelCartaoZonaAzul(dAOMotorista, dAOCartaoZonaAzul, dAOCompraCartaoZA, dAOPlaca);
        Placa placa = new Placa("KHX", "0066");
        CartaoZonaAzul cartao = modelCarttaoZA.comprar(motorista, placa);

    }

    @Test
    public void deveRecuperarUmCartaoCadastrado() throws DaoException, MotoristaException, PlacaException {
        modelCarttaoZA = new ModelCartaoZonaAzul(dAOMotorista, dAOCartaoZonaAzul, dAOCompraCartaoZA, dAOPlaca);
        CartaoZonaAzul c1 = cartoes.get(0);
        CartaoZonaAzul c2 = cartoes.get(1);
        CartaoZonaAzul c3 = cartoes.get(2);

        assertEquals(c1.getNumero(), modelCarttaoZA.recuperar(c1).getNumero());
        assertEquals(c2.getNumero(), modelCarttaoZA.recuperar(c2).getNumero());
        assertEquals(c3.getNumero(), modelCarttaoZA.recuperar(c3).getNumero());
    }
    @Ignore
    @Test
    public void deveRecuperarUltimoCartaoCompradoAPartirDaPlaca() throws DaoException, MotoristaException, PlacaException {
        motorista.creditar(new BigDecimal(100));
        dAOMotorista.cadastrar(motorista);
        dAOCartaoZonaAzul.cadastrar(new CartaoZonaAzulInfo("Garanhuns", new BigDecimal(2)));
        modelCarttaoZA = new ModelCartaoZonaAzul(dAOMotorista, dAOCartaoZonaAzul, dAOCompraCartaoZA, dAOPlaca);
        CartaoZonaAzul ultimo = modelCarttaoZA.comprar(motorista, new Placa("KHX", "0066"));
       
        CartaoZonaAzul ca = modelCarttaoZA.recuperarUltimo(new Placa("KHX", "0066"));

        assertEquals(ultimo.getPlaca().getLetras(), ca.getPlaca().getLetras());
        assertEquals(ultimo.getPlaca().getNumeros(), ca.getPlaca().getNumeros());

    }
    
    // O teste não passa
    @Test(expected = IllegalArgumentException.class)
    public void deveLancarExcecaoAnoMaiorQueAtual() throws DaoException{
        modelCarttaoZA.vendasNoMes(2018);
    }
    
    // O teste passa
    @Test(expected = NullPointerException.class)
    public void deverLancarExcecaoAnoNulo() throws DaoException{
        modelCarttaoZA.vendasNoMes(0);
    }
    
    // O teste não passa
    @Test
    public void deveLancarExcecaoCartaoZonaAzulNulo() throws DaoException{
        excption.expect(CartaoZAException.class);
        excption.expectMessage(CartaoZAException.NULL);
        dAOCartaoZonaAzul = null;
        dAOCartaoZonaAzul.vendasPorMes(1);
    }
    
    @Ignore
    @Test
    public void recuperarCartoesAtivosDoMotorista() throws DaoException, MotoristaException, PlacaException {
        modelCarttaoZA = new ModelCartaoZonaAzul(dAOMotorista, dAOCartaoZonaAzul, dAOCompraCartaoZA, dAOPlaca);
        List<CartaoZonaAzul> lista = modelCarttaoZA.CartoesAtivosPor(motoristas.get(0));
        assertEquals(3, lista.size());
    }
}
