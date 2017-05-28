/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.model;

import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.model.DAO.DaoMotoristaBD;
import com.zonaAzulDigital.model.DAO.interfaces.DAOMotorista;

/**
 *
 * @author JonasJr
 */
public class ModelMotorista implements Model<Motorista>{

    @Override
    public Motorista cadastrar(Motorista objeto) throws DaoException {
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

    @Override
    public Motorista deletar(Motorista objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
