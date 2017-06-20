/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.model;

import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.DAO.DaoPlacaBD;
import com.zonaAzulDigital.interfaces.DAOPlaca;
import com.zonaAzulDigital.interfaces.ModelPlacaInterface;

/**
 *
 * @author JonasJr
 */
public class ModelPlaca implements ModelPlacaInterface {

    @Override
    public Placa cadastrar(Placa objeto) throws DaoException {
        DAOPlaca daoPlaca = new DaoPlacaBD();
        objeto = (Placa) daoPlaca.cadastrar(objeto);
        return objeto;
    }

    @Override
    public Placa recuperar(Placa placa) throws DaoException {
        DAOPlaca daoPlaca = new DaoPlacaBD();
        placa = (Placa) daoPlaca.recuperar(placa.getLetras(), placa.getNumeros());

        return placa;
    }

    @Override
    public Placa recuperarId(int id) throws DaoException{
        DAOPlaca dAOPlaca = new DaoPlacaBD();
        return dAOPlaca.recuperarPorId(id);
    }

}
