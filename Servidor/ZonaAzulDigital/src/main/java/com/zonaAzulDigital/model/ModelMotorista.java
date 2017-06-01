/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.model;

import com.zonaAzulDigital.Excecao.CpfException;
import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.Excecao.LoginException;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.validacao.CPF;
import com.zonaAzulDigital.DAO.DaoMotoristaBD;
import com.zonaAzulDigital.interfaces.DAOMotorista;
import com.zonaAzulDigital.interfaces.ModelMotoristaInterface;

/**
 *
 * @author JonasJr
 */
public class ModelMotorista implements ModelMotoristaInterface {

    @Override
    public Motorista cadastrar(Motorista objeto) throws DaoException, CpfException {
        validar(objeto);
        DAOMotorista daoMotorista = new DaoMotoristaBD();
        objeto = (Motorista) daoMotorista.cadastrar(objeto);
        return objeto;
    }

    @Override
    public Motorista atualizar(Motorista objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Motorista recuperar(Motorista objeto) throws DaoException {
        DAOMotorista daoMotorista = new DaoMotoristaBD();
        objeto = daoMotorista.recuperar(objeto.getCpf());
        return objeto;
    }

    public boolean validar(Motorista motorista) throws DaoException, CpfException {
        CPF.validarCPF(motorista.getCpf());
        return true;
    }

    @Override
    public boolean login(long cpf, String senha) throws LoginException {
        return true;
    }

}
