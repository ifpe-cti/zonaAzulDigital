/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.model;

import com.zonaAzulDigital.DAO.DaoPlacaBD;
import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.Excecao.MotoristaException;
import com.zonaAzulDigital.Excecao.PlacaException;
import com.zonaAzulDigital.entidades.CartaoZonaAzul;
import com.zonaAzulDigital.entidades.CartaoZonaAzulInfo;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.interfaces.DAOCartaoZonaAzul;
import com.zonaAzulDigital.interfaces.DAOCompraCartaoZA;
import com.zonaAzulDigital.interfaces.DAOMotorista;
import com.zonaAzulDigital.interfaces.DAOPlaca;
import com.zonaAzulDigital.interfaces.ModelCartaoZonaAzulInterface;
import com.zonaAzulDigital.tests.DAO.DAOCartaoZonaAzulFake;
import com.zonaAzulDigital.tests.DAO.DAOCompraCartaoZAFake;
import com.zonaAzulDigital.tests.DAO.DAOMotoristaFake;
import java.math.BigDecimal;
import java.util.ArrayList;
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

    public ModelCartaoZonaAzulTest() {
    }

    @Rule
    public ExpectedException excption = ExpectedException.none();

    @Before
    public void antesDostestes() {
        dAOMotorista = new DAOMotoristaFake();
        dAOCompraCartaoZA = new DAOCompraCartaoZAFake();
        dAOCartaoZonaAzul = new DAOCartaoZonaAzulFake();
        dAOPlaca = new DaoPlacaBD();
        motorista = new Motorista("Jonas Ferreira", "106549011430", "123");
    }

    @Test
    public void deveRealizarUmaCompra() throws DaoException, MotoristaException, PlacaException {
        motorista.creditar(new BigDecimal(2));
        dAOMotorista.cadastrar(motorista);
        dAOCartaoZonaAzul.cadastrar(new CartaoZonaAzulInfo("Garanhuns", new BigDecimal(2)));
        modelCarttaoZA = new ModelCartaoZonaAzul(dAOMotorista, dAOCartaoZonaAzul, dAOCompraCartaoZA, dAOPlaca);
        Placa placa = new Placa("KHX", "0066");
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
        motorista.creditar(new BigDecimal(100));
        dAOMotorista.cadastrar(motorista);
        dAOCartaoZonaAzul.cadastrar(new CartaoZonaAzulInfo("Garanhuns", new BigDecimal(2)));
        modelCarttaoZA = new ModelCartaoZonaAzul(dAOMotorista, dAOCartaoZonaAzul, dAOCompraCartaoZA, dAOPlaca);
        CartaoZonaAzul c1 = modelCarttaoZA.comprar(motorista, new Placa("KHX", "0066"));
        CartaoZonaAzul c2 = modelCarttaoZA.comprar(motorista, new Placa("MUS", "2277"));
        CartaoZonaAzul c3 = modelCarttaoZA.comprar(motorista, new Placa("KFW", "8983"));

        List<CartaoZonaAzul> lista = ((DAOCompraCartaoZAFake) dAOCompraCartaoZA).getListaCartoes();
        ((DAOCartaoZonaAzulFake) dAOCartaoZonaAzul).setListaCartoes(lista);

        modelCarttaoZA = new ModelCartaoZonaAzul(dAOMotorista, dAOCartaoZonaAzul, dAOCompraCartaoZA, dAOPlaca);

        assertEquals(c1, modelCarttaoZA.recuperar(c1));
        assertEquals(c2, modelCarttaoZA.recuperar(c2));
        assertEquals(c3, modelCarttaoZA.recuperar(c3));
    }
    @Ignore
    @Test
    public void deveRecuperarUltimoCartaoCompradoAPartirDaPlaca() throws DaoException, MotoristaException, PlacaException {
        motorista.creditar(new BigDecimal(100));
        dAOMotorista.cadastrar(motorista);
        dAOCartaoZonaAzul.cadastrar(new CartaoZonaAzulInfo("Garanhuns", new BigDecimal(2)));
        modelCarttaoZA = new ModelCartaoZonaAzul(dAOMotorista, dAOCartaoZonaAzul, dAOCompraCartaoZA, dAOPlaca);
        CartaoZonaAzul ultimo = modelCarttaoZA.comprar(motorista, new Placa("KHX", "0066"));
        ultimo = modelCarttaoZA.comprar(motorista, new Placa("MUS", "2277"));
        ultimo = modelCarttaoZA.comprar(motorista, new Placa("KFW", "8983"));
        ultimo = modelCarttaoZA.comprar(motorista, new Placa("KHX", "0066"));

        List<CartaoZonaAzul> lista = ((DAOCompraCartaoZAFake) dAOCompraCartaoZA).getListaCartoes();
        ((DAOCartaoZonaAzulFake) dAOCartaoZonaAzul).setListaCartoes(lista);

        modelCarttaoZA = new ModelCartaoZonaAzul(dAOMotorista, dAOCartaoZonaAzul, dAOCompraCartaoZA, dAOPlaca);

        assertEquals(ultimo.getPlaca().getLetras(), modelCarttaoZA.recuperarUltimo(new Placa("KHX", "0066")).getPlaca().getLetras());
        assertEquals(ultimo.getPlaca().getNumeros(), modelCarttaoZA.recuperarUltimo(new Placa("KHX", "0066")).getPlaca().getNumeros());

    }
    @Ignore
    @Test
    public void recuperarCartoesAtivosDoMotorista() throws DaoException, MotoristaException, PlacaException {
        modelCarttaoZA = new ModelCartaoZonaAzul(dAOMotorista, dAOCartaoZonaAzul, dAOCompraCartaoZA, dAOPlaca);
        List<CartaoZonaAzul> lista = modelCarttaoZA.CartoesAtivosPor(motorista);
        assertEquals(3, lista.size());
    }
}
