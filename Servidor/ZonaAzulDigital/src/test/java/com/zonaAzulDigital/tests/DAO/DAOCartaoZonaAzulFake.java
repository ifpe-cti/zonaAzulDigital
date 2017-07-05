/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.tests.DAO;

import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.entidades.CartaoZonaAzul;
import com.zonaAzulDigital.entidades.CartaoZonaAzulInfo;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.interfaces.DAOCartaoZonaAzul;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JonasJr
 */
public class DAOCartaoZonaAzulFake implements DAOCartaoZonaAzul {

    private List<CartaoZonaAzul> cartoes;

    public DAOCartaoZonaAzulFake() {
        this.cartoes = new ArrayList<>();
    }

    @Override
    public CartaoZonaAzulInfo cadastrar(CartaoZonaAzulInfo cartaoZonaAzulInfo) throws DaoException {
        return cartaoZonaAzulInfo;
    }

    @Override
    public CartaoZonaAzul recuperarUltimo(Placa placa) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CartaoZonaAzul recuperarPorId(int id) throws DaoException {
        CartaoZonaAzul retorno = null;
        try {
            retorno = this.cartoes.get(id);
        } catch (NullPointerException ex) {

        }
        return retorno;
    }

    @Override
    public BigDecimal preco(String cidade) throws DaoException {
       return  new BigDecimal(2);
    }

    @Override
    public List<CartaoZonaAzul> listarTudo() {
        return cartoes;
    }

}
