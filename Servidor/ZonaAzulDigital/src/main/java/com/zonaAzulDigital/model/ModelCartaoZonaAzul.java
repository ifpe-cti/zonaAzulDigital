/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.model;

import com.zonaAzulDigital.Excecao.CartaoZAException;
import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.entidades.CartaoZonaAzul;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.Excecao.MotoristaException;
import com.zonaAzulDigital.Excecao.PlacaException;
import com.zonaAzulDigital.entidades.CartaoZonaAzulInfo;
import com.zonaAzulDigital.entidades.CompraCartaoZA;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.entidades.VendaMes;
import com.zonaAzulDigital.interfaces.DAOCartaoZonaAzul;
import com.zonaAzulDigital.interfaces.DAOCompraCartaoZA;
import com.zonaAzulDigital.interfaces.DAOMotorista;
import com.zonaAzulDigital.interfaces.DAOPlaca;
import com.zonaAzulDigital.interfaces.ModelCartaoZonaAzulInterface;
import com.zonaAzulDigital.interfaces.ModelPlacaInterface;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author JonasJr
 */
public class ModelCartaoZonaAzul implements ModelCartaoZonaAzulInterface {

    private final DAOMotorista daoMotorista;
    private final DAOCartaoZonaAzul daoCartaoZonaAzul;
    private final DAOCompraCartaoZA daoCompraCartaoZA;
    private final DAOPlaca daoPlaca;

    public ModelCartaoZonaAzul(DAOMotorista daoMotorista, DAOCartaoZonaAzul daoCartaoZonaAzul,
            DAOCompraCartaoZA daoCompraCartaoZA, DAOPlaca daoPlaca) {
        this.daoMotorista = daoMotorista;
        this.daoCartaoZonaAzul = daoCartaoZonaAzul;
        this.daoCompraCartaoZA = daoCompraCartaoZA;
        this.daoPlaca = daoPlaca;
    }

    @Override
    public CartaoZonaAzulInfo cadastrarDetalhes(CartaoZonaAzulInfo cartaoZonaAzulInfo) throws CartaoZAException, DaoException {
        if (cartaoZonaAzulInfo == null) {
            throw new CartaoZAException(CartaoZAException.NULL);
        }
        if (cartaoZonaAzulInfo.getCidade() == null || cartaoZonaAzulInfo.getCidade().isEmpty()) {
            throw new CartaoZAException(CartaoZAException.CIDADENULL);
        }
        if (cartaoZonaAzulInfo.getPreco() == null) {
            throw new CartaoZAException(CartaoZAException.PRECO);
        }

        return daoCartaoZonaAzul.cadastrar(cartaoZonaAzulInfo);
    }

    /**
     * Dado um motorista cadastrado e com credito suficiente, debita o valor do
     * cartão dos creditos do motorista, cria um novo cartao com a placa passada
     * e define a hora de inicio com a hora atual do sistema.
     *
     * @param motorista Motorista que compra o cartão
     * @param placa Veiculo para o qual sera ativado o cartão
     * @return Um cartão valido e ativo.
     * @throws MotoristaException - Se o motorista nao for valido, se não tiver
     * cridito suficiente
     * @throws DaoException
     * @throws PlacaException - Se a placa nao for valida
     */
    @Override
    public CartaoZonaAzul comprar(Motorista motorista, Placa placa) throws MotoristaException, DaoException, PlacaException {
        try {
            motorista = this.daoMotorista.recuperar(motorista.getCpf());

        } catch (NullPointerException ex) {
            throw new MotoristaException(MotoristaException.NULL, ex);
        }
        if (motorista == null) {
            throw new MotoristaException(MotoristaException.NAOENCONTRADO);
        }
        try {
            Placa p = this.daoPlaca.recuperar(placa.getLetras(), placa.getNumeros());
            if (p != null) {
                placa = p;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        ModelPlacaInterface mp = new ModelPlaca(daoPlaca);
        mp.validar(placa);

        BigDecimal preco = daoCartaoZonaAzul.preco("Garanhuns");
        CartaoZonaAzul novoCartaoZA = null;
        if (motorista.debitar(preco)) {
            novoCartaoZA = new CartaoZonaAzul(placa);
            novoCartaoZA.setValor(preco);
            CompraCartaoZA compraCartaoZA = new CompraCartaoZA(motorista, novoCartaoZA);
            compraCartaoZA = daoCompraCartaoZA.comprar(compraCartaoZA);
            novoCartaoZA = compraCartaoZA.getCartaoZonaAzul();

        } else {
            throw new MotoristaException(MotoristaException.CREDITOINSUFICIENTE);
        }
        return novoCartaoZA;
    }

    @Override
    public CartaoZonaAzul recuperar(CartaoZonaAzul cartaoZA) throws DaoException {
        cartaoZA = (CartaoZonaAzul) daoCartaoZonaAzul.recuperarPorId(cartaoZA.getNumero());
        return cartaoZA;
    }

    @Override
    public CartaoZonaAzul recuperarUltimo(Placa placa) throws DaoException, PlacaException {
        ModelPlacaInterface mp = new ModelPlaca(daoPlaca);

        mp.validar(placa);
        placa = daoPlaca.recuperar(placa.getLetras(), placa.getNumeros());

        CartaoZonaAzul cartaoZonaAzul = daoCartaoZonaAzul.recuperarUltimo(placa);
        cartaoZonaAzul.setTempoRestante(calculaTempoRestante(cartaoZonaAzul.getDataFim()).toString());
        return cartaoZonaAzul;
    }

    @Override
    public CartaoZonaAzul recuperaCartaoAtivo(Placa placa) throws PlacaException, DaoException {
        ModelPlacaInterface mp = new ModelPlaca(daoPlaca);
        mp.validar(placa);
        placa = daoPlaca.recuperar(placa.getLetras(), placa.getNumeros());

        CartaoZonaAzul cartaoAtivo = daoCartaoZonaAzul.recuperaCartaoAtivo(placa);
        if (cartaoAtivo.getNumero() != 0) {
            cartaoAtivo.setTempoRestante(calculaTempoRestante(cartaoAtivo.getDataFim()).toString());;
        }

        return cartaoAtivo;
    }

    @Override
    public List<CartaoZonaAzul> CartoesAtivosPor(Motorista motorista) {
        List<CartaoZonaAzul> cartaoZonaAzuls = daoCartaoZonaAzul.listarCartoesAtivos(motorista);

        for (CartaoZonaAzul cartaoZonaAzul : cartaoZonaAzuls) {

            cartaoZonaAzul.setTempoRestante(calculaTempoRestante(cartaoZonaAzul.getDataFim()).toString());
        }

        return cartaoZonaAzuls;
    }

    /**
     * Passada a data e hora que o cartao expira
     * @param dataFim
     * @return
     */
    public LocalTime calculaTempoRestante(Date dataFim) {
        LocalTime horaExpirar = LocalDateTime.ofInstant(dataFim.toInstant(), ZoneId.systemDefault()).toLocalTime();
        long tempoEmSegundos = ChronoUnit.SECONDS.between(LocalTime.now(), horaExpirar);
        if (tempoEmSegundos < 0) {
            tempoEmSegundos = 0;
        }
        return LocalTime.ofSecondOfDay(tempoEmSegundos);

    }

    @Override
    public List<VendaMes> vendasNoMes(int ano) throws DaoException {
        List<Object[]> lista = daoCartaoZonaAzul.vendasPorMes(ano);
        List<VendaMes> vendasMes = new ArrayList<>();
        for (Object[] vendasMe : lista) {
            int i = (int) vendasMe[0];
            long l = (long) vendasMe[1];
            vendasMes.add(new VendaMes(i, l));

        }
        return vendasMes;
    }

    @Override
    public List<CartaoZonaAzul> recuperarTodosCartoesPor(Motorista motorista) {
        return daoCartaoZonaAzul.recuperarCartoesPor(motorista);
    }

}
