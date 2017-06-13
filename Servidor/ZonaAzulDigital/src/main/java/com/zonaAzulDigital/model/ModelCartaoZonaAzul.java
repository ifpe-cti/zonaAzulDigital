/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.model;

import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.entidades.CartaoZonaAzul;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.DAO.DaoCartaoZonaAzulBD;
import com.zonaAzulDigital.DAO.DaoMotoristaBD;
import com.zonaAzulDigital.DAO.DaoPlacaBD;
import com.zonaAzulDigital.Excecao.MotoristaException;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.interfaces.DAOCartaoZonaAzul;
import com.zonaAzulDigital.interfaces.DAOMotorista;
import com.zonaAzulDigital.interfaces.DAOPlaca;
import com.zonaAzulDigital.interfaces.ModelCartaoZonaAzulInterface;
import java.math.BigDecimal;

/**
 *
 * @author JonasJr
 */
public class ModelCartaoZonaAzul implements ModelCartaoZonaAzulInterface {

    @Override
    public CartaoZonaAzul comprar(Motorista motorista, Placa placa) throws MotoristaException, DaoException {
        DAOMotorista daoMotorista = new DaoMotoristaBD();
        try {
            daoMotorista.recuperarPorId(motorista.getId());
        } catch (NullPointerException ex) {
            throw new MotoristaException(MotoristaException.NULL);
        }
        DAOCartaoZonaAzul daoCartaoZonaAzul = new DaoCartaoZonaAzulBD();
        BigDecimal preco = daoCartaoZonaAzul.preco("Garanhuns");
        CartaoZonaAzul novoCartaoZA = null;
        if (motorista.debitar(preco)) {
            novoCartaoZA = new CartaoZonaAzul(placa);
            novoCartaoZA.setValor(preco);
            novoCartaoZA = (CartaoZonaAzul) daoCartaoZonaAzul.comprar(novoCartaoZA, motorista);
        }
        return novoCartaoZA;
    }

    @Override
    public CartaoZonaAzul recuperar(CartaoZonaAzul cartaoZA) throws DaoException {
        DAOCartaoZonaAzul daoCartaoZonaAzul = new DaoCartaoZonaAzulBD();
        cartaoZA = (CartaoZonaAzul) daoCartaoZonaAzul.recuperarPorId(cartaoZA.getNumero());
        return cartaoZA;
    }

    @Override
    public CartaoZonaAzul recuperarUltimo(Placa placa) throws DaoException {
        DAOPlaca daoPlaca = new DaoPlacaBD();

        placa = daoPlaca.recuperar(placa.getLetras(), placa.getNumeros());

        CartaoZonaAzul cartaoZonaAzul = new CartaoZonaAzul();
        DAOCartaoZonaAzul daoCartaoZonaAzul = new DaoCartaoZonaAzulBD();
        cartaoZonaAzul = (CartaoZonaAzul) daoCartaoZonaAzul.recuperarUltimo(placa);
        return cartaoZonaAzul;
    }

}
