/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.interfaces;

import com.zonaAzulDigital.Excecao.CpfException;
import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.Excecao.LoginException;
import com.zonaAzulDigital.Excecao.MotoristaException;
import com.zonaAzulDigital.entidades.Motorista;
import java.util.List;

/**
 *
 * @author JonasJr
 */
public interface ModelMotoristaInterface {
    Motorista cadastrar(Motorista motorista) throws DaoException, CpfException,MotoristaException;
    Motorista atualizar(Motorista motorista) throws DaoException, CpfException, MotoristaException;
    Motorista recuperar(Motorista motorista) throws DaoException, CpfException;
    Motorista login(String cpf, String senha) throws LoginException;
    List<Motorista> listarTodos();
}
