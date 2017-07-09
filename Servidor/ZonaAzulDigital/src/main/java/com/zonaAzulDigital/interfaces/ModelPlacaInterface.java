/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.interfaces;

import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.Excecao.PlacaException;
import com.zonaAzulDigital.entidades.Placa;

/**
 *
 * @author JonasJr
 */
public interface ModelPlacaInterface {
    public Placa recuperar(Placa placa) throws DaoException;
    public Placa recuperarId(int id) throws DaoException;
    public boolean validar(Placa p) throws PlacaException; 
}
