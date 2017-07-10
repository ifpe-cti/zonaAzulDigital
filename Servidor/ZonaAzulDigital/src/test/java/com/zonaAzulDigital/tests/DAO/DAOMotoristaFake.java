/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.tests.DAO;

import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.Excecao.LoginException;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.interfaces.DAOMotorista;

import com.zonaAzulDigital.tests.DAO.base.Motoristas;
import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Samuel
 */
public class DAOMotoristaFake implements DAOMotorista {

    List<Motorista> listaMotoristas;

    public DAOMotoristaFake(Motoristas motoristas) {
        this.listaMotoristas = motoristas.getMotoristas();
    }
    
    
    
    @Override
    public Motorista cadastrar(Motorista motorista) throws DaoException {
        motorista.setId(listaMotoristas.size() + 1);
        this.listaMotoristas.add(motorista);
        return motorista;        
    }
    
    @Override
    public Motorista recuperar(String cpf) throws DaoException {
        Motorista m = null;
        for (Motorista motorista : listaMotoristas) {
            if (motorista.getCpf().equals(cpf)) {
                m = motorista;
            }
        }
        return m;
    }
    
    @Override
    public Motorista recuperarPorId(int id) throws DaoException {
          Motorista m = null;
        for (Motorista motorista : listaMotoristas) {
            if (motorista.getId() == id) {
                m = motorista;
            }
        }
        return m;
    }
    
    @Override
    public Motorista atualizar(Motorista motorista) throws DaoException {
        Motorista mRetorno = null;
        for (Motorista m : listaMotoristas) {
            if (m.getCpf().equals(motorista.getCpf()) ){
                m.setCredito(motorista.getCredito());
                m.setNome(motorista.getNome());
                m.setSenha(motorista.getSenha());
                
                mRetorno = m;
            }
        }
        if (mRetorno == null ){ 
            throw new DaoException(DaoException.NAOATUALIZADO);
        }
        return mRetorno;
    }
    
    @Override
    public Motorista login(String cpf, String senha) throws LoginException {
        Motorista m = null;
        for (Motorista motorista : listaMotoristas) {
            if (motorista.getCpf().equals(cpf) && motorista.getSenha().equals(senha)) {
                m = motorista;
            }
        }
        if (m == null) { 
           throw new LoginException(LoginException.FALHOU);
        }
        return m;
    }
    
    @Override
    public List<Motorista> listarTudo() {
        return listaMotoristas;
    }
    
}
