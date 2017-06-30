/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.tests.DAO;

import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.interfaces.DAOPlaca;
import java.util.List;

/**
 *
 * @author JonasJr
 */
public class DAOPlacaFake implements DAOPlaca{

    @Override
    public Placa cadastrar(Placa objeto) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Placa recuperar(String letras, String numeros) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Placa recuperarPorId(Object objeto) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Placa> listarTudo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
