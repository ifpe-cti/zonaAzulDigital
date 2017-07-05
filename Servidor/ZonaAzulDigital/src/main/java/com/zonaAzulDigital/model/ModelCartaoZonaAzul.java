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
    public CartaoZonaAzul comprar(Motorista motorista, Placa placa) throws MotoristaException, DaoException,PlacaException {
        try {
            motorista = this.daoMotorista.recuperarPorId(motorista.getId());
        } catch (NullPointerException ex) {
            throw new MotoristaException(MotoristaException.NULL, ex);
        }
        try {

           placa = this.daoPlaca.recuperar(placa.getLetras(), placa.getNumeros());
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
            daoCompraCartaoZA.comprar(compraCartaoZA);

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
    public CartaoZonaAzul recuperarUltimo(Placa placa) throws DaoException {

        placa = daoPlaca.recuperar(placa.getLetras(), placa.getNumeros());

        CartaoZonaAzul cartaoZonaAzul = new CartaoZonaAzul();
        cartaoZonaAzul = (CartaoZonaAzul) daoCartaoZonaAzul.recuperarUltimo(placa);
        return cartaoZonaAzul;
    }

}
