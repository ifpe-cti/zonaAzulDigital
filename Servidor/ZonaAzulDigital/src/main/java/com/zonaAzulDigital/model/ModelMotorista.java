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
import com.zonaAzulDigital.Excecao.MotoristaException;
import com.zonaAzulDigital.interfaces.DAOMotorista;
import com.zonaAzulDigital.interfaces.ModelMotoristaInterface;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author JonasJr
 */
public class ModelMotorista implements ModelMotoristaInterface {

    private DAOMotorista daoMotorista;

    public ModelMotorista(DAOMotorista daoMotorista) {
        this.daoMotorista = daoMotorista;
    }

    @Override
    public Motorista cadastrar(Motorista objeto) throws DaoException, CpfException, MotoristaException {
        validar(objeto);

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

    public boolean validar(Motorista motorista) throws DaoException, CpfException, MotoristaException {
        if (motorista == null) {
            throw new MotoristaException(MotoristaException.NULL);
        }

        if (motorista.getNome() == null || motorista.getNome().isEmpty()) {
            throw new MotoristaException(MotoristaException.NOMEOBRIGATORIO);
        }

        if (motorista.getSenha() == null || motorista.getSenha().isEmpty()) {
            throw new MotoristaException(MotoristaException.SENHAOBRIGATORIA);
        }
        
        if (motorista.getCredito() == null || motorista.getCredito().compareTo(BigDecimal.ZERO)< 0){
            throw new MotoristaException(MotoristaException.CREDITOINVALIDO);
        }

        CPF.validarCPF(motorista.getCpf());

        return true;
    }

    @Override
    public Motorista login(String cpf, String senha) throws LoginException {

        return daoMotorista.login(cpf, senha);
    }

    @Override
    public List<Motorista> listarTodos() {
        return daoMotorista.listarTudo();
    }

}
