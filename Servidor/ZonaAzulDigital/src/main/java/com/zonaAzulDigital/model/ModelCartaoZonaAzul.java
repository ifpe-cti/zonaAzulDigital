/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.model;

import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.entidades.CartaoZonaAzul;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.Excecao.MotoristaException;
import com.zonaAzulDigital.Excecao.PlacaException;
import com.zonaAzulDigital.entidades.CompraCartaoZA;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.interfaces.DAOCartaoZonaAzul;
import com.zonaAzulDigital.interfaces.DAOCompraCartaoZA;
import com.zonaAzulDigital.interfaces.DAOMotorista;
import com.zonaAzulDigital.interfaces.DAOPlaca;
import com.zonaAzulDigital.interfaces.ModelCartaoZonaAzulInterface;
import com.zonaAzulDigital.interfaces.ModelPlacaInterface;
import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
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
    public CartaoZonaAzul recuperarUltimo(Placa placa) throws DaoException ,PlacaException{
        ModelPlacaInterface mp = new ModelPlaca(daoPlaca);
        
        mp.validar(placa);
        placa = daoPlaca.recuperar(placa.getLetras(), placa.getNumeros());

        CartaoZonaAzul cartaoZonaAzul = (CartaoZonaAzul) daoCartaoZonaAzul.recuperarUltimo(placa);
        cartaoZonaAzul.setTempoRestante(calculaTempoRestante(cartaoZonaAzul.getDataFim()).toString());
        return cartaoZonaAzul;
    }

    @Override
    public List<CartaoZonaAzul> CartoesAtivosPor(Motorista motorista) {
        List<CartaoZonaAzul> cartaoZonaAzuls = daoCartaoZonaAzul.listarCartoesAtivos(motorista);

        for (CartaoZonaAzul cartaoZonaAzul : cartaoZonaAzuls) {
            
            cartaoZonaAzul.setTempoRestante(calculaTempoRestante(cartaoZonaAzul.getDataFim()).toString());
        }

        return cartaoZonaAzuls;
    }

    public LocalTime calculaTempoRestante(Date data) {
        LocalTime horaExpirar = LocalDateTime.ofInstant(data.toInstant(), ZoneId.systemDefault()).toLocalTime();
        long tempoEmSegundos = ChronoUnit.SECONDS.between(LocalTime.now(), horaExpirar);
        if(tempoEmSegundos < 0){
            tempoEmSegundos = 0;
        }
        return LocalTime.ofSecondOfDay(tempoEmSegundos);

    }

    public List<Long> vendarNoMes(int ano) throws DaoException {
        return daoCartaoZonaAzul.vendasPorMes(ano);
    }

}
