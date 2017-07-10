/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.tests.DAO;

import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.entidades.CartaoZonaAzul;
import com.zonaAzulDigital.entidades.CartaoZonaAzulInfo;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.entidades.VendaMes;
import com.zonaAzulDigital.interfaces.DAOCartaoZonaAzul;
import com.zonaAzulDigital.tests.DAO.base.Cartoes;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author JonasJr
 */
public class DAOCartaoZonaAzulFake implements DAOCartaoZonaAzul {

    private List<CartaoZonaAzul> cartoes;

    public DAOCartaoZonaAzulFake(Cartoes cartoes) {
        this.cartoes = cartoes.getCartoes();
    }

    @Override
    public CartaoZonaAzulInfo cadastrar(CartaoZonaAzulInfo cartaoZonaAzulInfo) throws DaoException {
        return cartaoZonaAzulInfo;
    }

    @Override
    public CartaoZonaAzul recuperarUltimo(Placa placa) throws DaoException {
        CartaoZonaAzul ultimo = null;
        for (CartaoZonaAzul carto : cartoes) {
            if (carto.getPlaca().getLetras().equals(placa.getLetras())
                    && carto.getPlaca().getNumeros().equals(placa.getNumeros())) {
                if (ultimo == null) {
                    ultimo = carto;
                } else if (ultimo.getDataFim().compareTo(carto.getDataFim()) == -1) {
                    ultimo = carto;
                }
            }
        }
        return ultimo;
    }

    @Override
    public CartaoZonaAzul recuperarPorId(int id) throws DaoException {
        CartaoZonaAzul retorno = null;
        for (CartaoZonaAzul carto : cartoes) {
            if (carto.getNumero() == id) {
                retorno = carto;
                break;
            }
        }
        return retorno;
    }

    @Override
    public BigDecimal preco(String cidade) throws DaoException {
        return new BigDecimal(2);
    }

    @Override
    public List<CartaoZonaAzul> listarTudo() {
        return cartoes;
    }

    @Override
    public List<CartaoZonaAzul> listarCartoesAtivos(Motorista m) {
        List<CartaoZonaAzul> cartoesAtivos = new ArrayList<>();
        if (m.getCpf().equals("10654901430")) {
            cartoesAtivos.add(new CartaoZonaAzul(new Placa("MUS", "2277")));
            cartoesAtivos.add(new CartaoZonaAzul(new Placa("KHX", "0066")));
            cartoesAtivos.add(new CartaoZonaAzul(new Placa("KFW", "8983")));
        }
        return cartoesAtivos;
    }

    public void setListaCartoes(List<CartaoZonaAzul> lista) {
        this.cartoes = lista;
    }

    @Override
    public List<Object[]> vendasPorMes(int ano) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Placa> recuperarCartoesPor(Motorista motorista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
