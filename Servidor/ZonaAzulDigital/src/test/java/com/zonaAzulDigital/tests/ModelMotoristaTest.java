/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.tests;

import com.zonaAzulDigital.Excecao.CpfException;
import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.Excecao.LoginException;
import com.zonaAzulDigital.Excecao.MotoristaException;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.interfaces.DAOMotorista;
import com.zonaAzulDigital.interfaces.ModelMotoristaInterface;
import com.zonaAzulDigital.model.ModelMotorista;
import com.zonaAzulDigital.tests.DAO.DAOMotoristaFake;
import com.zonaAzulDigital.tests.DAO.base.Motoristas;
import java.math.BigDecimal;
import java.util.List;
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
public class ModelMotoristaTest {

    public ModelMotoristaInterface md;

    public Motorista m;
    public Motorista m1, m2, m3;

    @Rule
    public ExpectedException excecao = ExpectedException.none();

 @Before
    public void before() {
        md = new ModelMotorista(new DAOMotoristaFake(new Motoristas()));
        m1 = new Motorista(0, "Samuel", "11791558402", BigDecimal.ZERO, "samuel");
        m2 = new Motorista(0, "Carlos Eduardo", "04982857407", BigDecimal.ZERO, "cadu");
        m3 = new Motorista(0, "Jonas", "10654901430", BigDecimal.ZERO, "xonas");
    }

    @Test
    public void testaCadastroSimplesdeMotorista() throws DaoException, CpfException, MotoristaException {
        m = new Motorista(0, "Samuel", "11791558402", BigDecimal.ZERO, "alabala");
        Motorista motorista = md.cadastrar(m);
        assertEquals("Samuel", motorista.getNome());
    }

    @Test
    public void deveDispararExcecaoDeCPFVazio() throws CpfException, DaoException, MotoristaException {
        excecao.expect(CpfException.class);
        excecao.expectMessage(CpfException.NULL);
        m = new Motorista(1, "Samuel", "", BigDecimal.ZERO, "alabala");
        md.cadastrar(m);
    }

    @Test
    public void deveDispararExcecaoDeCPFNull() throws CpfException, DaoException, MotoristaException {
        excecao.expect(CpfException.class);
        excecao.expectMessage(CpfException.NULL);
        m = new Motorista(1, "Samuel", null, BigDecimal.ZERO, "alabala");
        md.cadastrar(m);
    }

    @Test
    public void deveDispararExcecaoDeMotoristaVazio() throws DaoException, CpfException, MotoristaException {
        excecao.expect(MotoristaException.class);
        m = new Motorista();
        md.cadastrar(m);
    }

    @Test
    public void deveDispararExcecaoDeMotoristaNull() throws DaoException, CpfException, MotoristaException {
        excecao.expect(MotoristaException.class);
        excecao.expectMessage(MotoristaException.NULL);
        m = null;
        md.cadastrar(m);
    }

    @Test
    public void deveDispararExcecaoDeMotoristaComNomeVazio() throws DaoException, CpfException, MotoristaException {
        excecao.expect(MotoristaException.class);
        excecao.expectMessage(MotoristaException.NOMEOBRIGATORIO);
        md.cadastrar(new Motorista(1, "", "11791558402", BigDecimal.ZERO, "samuel"));
    }

    @Test
    public void deveDispararExcecaoDeMotoristaComNomeNull() throws DaoException, CpfException, MotoristaException {
        excecao.expect(MotoristaException.class);
        excecao.expectMessage(MotoristaException.NOMEOBRIGATORIO);
        md.cadastrar(new Motorista(1, null, "11791558402", BigDecimal.ZERO, "samuel"));
    }

    @Test
    public void deveDispararExcecaoDeMotoristaComSenhaVazia() throws DaoException, CpfException, MotoristaException {
        excecao.expect(MotoristaException.class);
        excecao.expectMessage(MotoristaException.SENHAOBRIGATORIA);
        md.cadastrar(new Motorista(1, "Samuel", "11791558402", BigDecimal.ZERO, ""));
    }

    @Test
    public void deveDispararExcecaoDeMotoristaComSenhaNull() throws DaoException, CpfException, MotoristaException {
        excecao.expect(MotoristaException.class);
        excecao.expectMessage(MotoristaException.SENHAOBRIGATORIA);
        md.cadastrar(new Motorista(1, "Samuel", "11791558402", BigDecimal.ZERO, null));
    }

    @Test
    public void deveDispararExcecaoDeMotoristaComCreditoNull() throws DaoException, CpfException, MotoristaException {
        excecao.expect(MotoristaException.class);

        md.cadastrar(new Motorista(1, "Samuel", "11791558402", null, "Samuel"));
    }

    @Test
    public void deveDispararExcecaoDeMotoristaComCreditoNegativo() throws DaoException, CpfException, MotoristaException {
        excecao.expect(MotoristaException.class);
        excecao.expectMessage(MotoristaException.CREDITOINVALIDO);
        md.cadastrar(new Motorista(1, "Samuel", "11791558402", new BigDecimal(-1), "Samuel"));
    }

    @Test
    public void deveRecuperarMotoristaPorCpf() throws DaoException, CpfException, MotoristaException {
        md.cadastrar(m1);
        md.cadastrar(m2);
        md.cadastrar(m3);

        Motorista motoristaRecuperado = md.recuperar(m1);

        assertEquals("Samuel", motoristaRecuperado.getNome());
    }

    @Test
    public void deveFazerLoginDoMotorista() throws DaoException, CpfException, MotoristaException, LoginException {
        md.cadastrar(m1);
        md.cadastrar(m2);
        md.cadastrar(m3);

        Motorista motoristaRecuperado = md.login(m1.getCpf(), m1.getSenha());

        assertEquals("Samuel", motoristaRecuperado.getNome());
    }

    @Test
    public void deveLevantarExcecaoComLoginInvalido() throws DaoException, MotoristaException, CpfException, LoginException {
        excecao.expect(LoginException.class);
        excecao.expectMessage(LoginException.FALHOU);

        md.cadastrar(m1);

        md.login("", "");
    }

    @Test
    public void deveAtualizarMotorista() throws DaoException, CpfException, MotoristaException {
        md.cadastrar(m1);

        Motorista m = new Motorista(0, "Samuel Soares", "11791558402", BigDecimal.ZERO, "samuleu");

        Motorista mAtualizado = md.atualizar(m);

        assertEquals(m.getNome(), mAtualizado.getNome());
    }

    @Test
    public void deveRecuperarMotoristaDaLista() throws DaoException, CpfException, MotoristaException {
        md.cadastrar(m1);
        Motorista m = md.recuperar(m1);
        assertEquals("Samuel", m.getNome());
    }

    @Test
    public void deveLevantarExcecaoQuandoRecuperarMotoristaDaLista() throws DaoException, CpfException, MotoristaException {
        excecao.expect(DaoException.class);
        md.cadastrar(m1);
        Motorista m = md.recuperar(m1);
        if (m != null) {
            throw new DaoException("Não foi possivel atualizar");
        }
    }

    @Ignore
    @Test
    public void deveRetornarUmaListaDeMotoristasCadastrados() throws DaoException, CpfException, MotoristaException {
        
        List<Motorista> lista = md.listarTodos();

        assertEquals("Jonas Ferreira Leal Junior", lista.get(0).getNome());
        assertEquals("Jonas Ferreira Leal", lista.get(1).getNome());
        assertEquals("Carlos Eduardo", lista.get(2).getNome());
    }
}
