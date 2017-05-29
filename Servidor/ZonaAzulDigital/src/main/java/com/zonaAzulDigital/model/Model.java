/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.model;

import com.zonaAzulDigital.Excecao.DaoException;

/**
 *
 * @author JonasJr
 */
public interface Model<T> {

    T cadastrar(T objeto) throws DaoException;

    T atualizar(T objeto) throws DaoException;

    T recuperar(T objeto) throws DaoException;

    T deletar(T objeto) throws DaoException;
}
