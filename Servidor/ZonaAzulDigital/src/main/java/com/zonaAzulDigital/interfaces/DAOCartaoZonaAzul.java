/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.interfaces;

import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.entidades.CartaoZonaAzul;
import com.zonaAzulDigital.entidades.CartaoZonaAzulInfo;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.entidades.VendaMes;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author JonasJr
 */
public interface DAOCartaoZonaAzul {

//    public CartaoZonaAzul comprar(CartaoZonaAzul cartaoZonaAzul, Motorista motorista) throws DaoException;
    public CartaoZonaAzulInfo cadastrar(CartaoZonaAzulInfo cartaoZonaAzulInfo) throws DaoException;

    public CartaoZonaAzul recuperarUltimo(Placa placa) throws DaoException;

    public CartaoZonaAzul recuperarPorId(int id) throws DaoException;

    public BigDecimal preco(String cidade) throws DaoException;

    public List<CartaoZonaAzul> listarCartoesAtivos(Motorista m);

    public List<CartaoZonaAzul> listarTudo();

    public List<Object[]> vendasPorMes(int ano) throws DaoException;

    public List<CartaoZonaAzul> recuperarCartoesPor(Motorista motorista);
}
