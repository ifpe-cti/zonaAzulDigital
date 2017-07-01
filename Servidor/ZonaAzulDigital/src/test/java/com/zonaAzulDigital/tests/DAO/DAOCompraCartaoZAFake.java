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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JonasJr
 */
public class DAOCompraCartaoZAFake implements DAOCompraCartaoZA{
    private List<CompraCartaoZA> compraCartaoZAs;
    

    public DAOCompraCartaoZAFake() {
        compraCartaoZAs = new ArrayList<>();
    }
    
    
    
    @Override
    public CompraCartaoZA comprar(CompraCartaoZA compraCartaoZA) throws DaoException {
        compraCartaoZA.setId(compraCartaoZAs.size() + 1);
        compraCartaoZAs.add(compraCartaoZA);
        return compraCartaoZA;
    }

    @Override
    public CompraCartaoZA recuperarPor(int id) {
        return  compraCartaoZAs.get(id);
    }

    @Override
    public List<CompraCartaoZA> recuperarPor(Motorista motorista) throws DaoException {
        List<CompraCartaoZA> returno = new ArrayList<>();
        for (CompraCartaoZA compraCartaoZA : compraCartaoZAs) {
            if(compraCartaoZA.getMotorista().equals(motorista)){
                returno.add(compraCartaoZA);
            }
        }
        return returno;
    }
    
}
