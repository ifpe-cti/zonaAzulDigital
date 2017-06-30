/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.tests.DAO;

import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.entidades.CompraCartaoZA;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.interfaces.DAOCompraCartaoZA;
import java.util.List;

/**
 *
 * @author JonasJr
 */
public class DAOCompraCartaoZAFake implements DAOCompraCartaoZA{

    @Override
    public CompraCartaoZA comprar(CompraCartaoZA compraCartaoZA) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CompraCartaoZA recuperarPor(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CompraCartaoZA> recuperarPor(Motorista motorista) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
