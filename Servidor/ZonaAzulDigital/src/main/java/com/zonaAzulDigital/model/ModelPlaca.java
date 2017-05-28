/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.model;

import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.model.DAO.DaoPlacaBD;
import com.zonaAzulDigital.model.DAO.interfaces.DAOPlaca;

/**
 *
 * @author JonasJr
 */
public class ModelPlaca implements Model<Placa> {

    @Override
    public Placa cadastrar(Placa objeto) throws DaoException {
        DAOPlaca daoPlaca = new DaoPlacaBD();
        objeto = (Placa) daoPlaca.cadastrar(objeto);
        return objeto;
    }

    @Override
    public Placa atualizar(Placa objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Placa recuperar(Placa objeto) throws DaoException {
        DAOPlaca daoPlaca = new DaoPlacaBD();
        objeto = (Placa) daoPlaca.recuperar(objeto.getLetras(), objeto.getNumeros());

        return objeto;
    }

    @Override
    public Placa deletar(Placa objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
