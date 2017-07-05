/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.interfaces;

import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.Excecao.LoginException;
import com.zonaAzulDigital.entidades.Adminstrador;
import java.util.List;

/**
 *
 * @author JonasJr
 */
public interface ModelAdministradorInterface {

    public Adminstrador cadastrar(Adminstrador adm) throws DaoException;

    public Adminstrador recuperar(Adminstrador adm) throws DaoException;

    public Adminstrador login(String cpf, String senha) throws DaoException , LoginException;

    public List<Adminstrador> listarTudo();

}
