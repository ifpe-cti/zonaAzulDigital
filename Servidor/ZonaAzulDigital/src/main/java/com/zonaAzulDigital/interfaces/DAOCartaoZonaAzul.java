/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.interfaces;

import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.entidades.CartaoZonaAzul;
import com.zonaAzulDigital.entidades.Placa;
import java.util.List;

/**
 *
 * @author JonasJr
 */
public interface DAOCartaoZonaAzul {
    public CartaoZonaAzul cadastrar(CartaoZonaAzul cartaoZonaAzul) throws DaoException;

    public CartaoZonaAzul recuperarUltimo(Placa placa) throws DaoException;

    public CartaoZonaAzul recuperarPorId(int id) throws DaoException;

    public List<CartaoZonaAzul> listarTudo();
}
