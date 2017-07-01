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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Samuel
 */
public class DAOMotoristaFake implements DAOMotorista {

    List<Motorista> motoristas = new ArrayList<>();
    
    @Override
    public Motorista cadastrar(Motorista motorista) throws DaoException {
        motorista.setId(motoristas.size() + 1);
        this.motoristas.add(motorista);
        return motorista;        
    }
    
    @Override
    public Motorista recuperar(String cpf) throws DaoException {
        Motorista m = null;
        for (Motorista motorista : motoristas) {
            if (motorista.getCpf().equals(cpf)) {
                m = motorista;
            }
        }
        return m;
    }
    
    @Override
    public Motorista recuperarPorId(int id) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Motorista atualizar(Motorista motorista) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Motorista login(String cpf, String senha) throws LoginException {
        Motorista m = null;
        for (Motorista motorista : motoristas) {
            if (motorista.getCpf().equals(cpf) && motorista.getSenha().equals(senha)) {
                m = motorista;
            }
        }
        return m;
    }
    
    @Override
    public List<Motorista> listarTudo() {
        return motoristas;
    }
    
}
