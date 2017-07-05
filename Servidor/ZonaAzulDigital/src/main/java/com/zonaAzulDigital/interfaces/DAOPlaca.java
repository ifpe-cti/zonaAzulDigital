/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.interfaces;

import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.entidades.Placa;
import java.util.List;

/**
 *
 * @author JonasJr
 */
public interface DAOPlaca {

    public Placa cadastrar(Placa objeto) throws DaoException;

    public Placa recuperar(String letras, String numeros) throws DaoException;

    public Placa recuperarPorId(int id) throws DaoException;

    public List<Placa> listarTudo();

}
