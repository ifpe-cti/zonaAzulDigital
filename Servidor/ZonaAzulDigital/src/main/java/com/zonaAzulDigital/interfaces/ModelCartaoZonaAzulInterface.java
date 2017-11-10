/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.interfaces;

import com.zonaAzulDigital.Excecao.CartaoZAException;
import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.Excecao.MotoristaException;
import com.zonaAzulDigital.Excecao.PlacaException;
import com.zonaAzulDigital.entidades.CartaoZonaAzul;
import com.zonaAzulDigital.entidades.CartaoZonaAzulInfo;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.entidades.VendaMes;
import java.util.List;

/**
 *
 * @author JonasJr
 */
public interface ModelCartaoZonaAzulInterface {

    CartaoZonaAzulInfo cadastrarDetalhes(CartaoZonaAzulInfo cartaoZonaAzulInfo) throws CartaoZAException, DaoException;

    /**
     * *
     * Se receber um motorista cadastrado no banco que contenha credito
     * suficiente e uma placa valida, realiza a compra e retorna um novo cartão
     * de Zona Azul valido/ativo
     *
     * @param motorista
     * @param placa
     * @return
     * @throws MotoristaException - Se o motorista não tiver cadastrado ou se
     * nao tiver credito suficiente
     * @throws DaoException
     * @throws PlacaException - Se a placa nao for valida.
     */
    CartaoZonaAzul comprar(Motorista motorista, Placa placa) throws MotoristaException, DaoException, PlacaException;

    /**
     * *
     * Se receber um cartão Zona Azul com o numero, retorna todos os dados desse
     * cartão.
     *
     * @param cartaoZA
     * @return CartaoZonaAzul com todos os dados.
     * @throws DaoException
     */
    CartaoZonaAzul recuperar(CartaoZonaAzul cartaoZA) throws DaoException;

    CartaoZonaAzul recuperarUltimo(Placa placa) throws DaoException, PlacaException;

    List<CartaoZonaAzul> CartoesAtivosPor(Motorista m);

    List<VendaMes> vendasNoMes(int ano) throws DaoException;

    List<CartaoZonaAzul> recuperarTodosCartoesPor(Motorista motorista);

    /**
     * Se receber uma placa valida, retorna o ultimo cartão ativo para essa
     * placa.
     *
     * @param placa
     * @return CartaoZonaAzul vazio se nao houver cartão ativo
     * @throws PlacaException - Se a placa nao for valida
     * @throws DaoException - Outro erro do banco de dados
     */
    CartaoZonaAzul recuperaCartaoAtivo(Placa placa) throws PlacaException, DaoException;
}
