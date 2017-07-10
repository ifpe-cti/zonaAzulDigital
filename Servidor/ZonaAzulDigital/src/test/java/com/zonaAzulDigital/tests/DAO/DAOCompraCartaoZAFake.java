/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.tests.DAO;

import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.entidades.CartaoZonaAzul;
import com.zonaAzulDigital.entidades.CompraCartaoZA;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.interfaces.DAOCompraCartaoZA;
import com.zonaAzulDigital.tests.DAO.base.Compras;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JonasJr
 */
public class DAOCompraCartaoZAFake implements DAOCompraCartaoZA{
    private List<CompraCartaoZA> compraCartaoZAs;
    
    public DAOCompraCartaoZAFake(Compras compras) {
        compraCartaoZAs = compras.comprasCartaoAZs();
    }
    
    
    
    @Override
    public CompraCartaoZA comprar(CompraCartaoZA compraCartaoZA) throws DaoException {
        compraCartaoZA.getCartaoZonaAzul().setNumero(compraCartaoZAs.size() + 1);
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

    public List<CompraCartaoZA> getCompraCartaoZAs() {
        return compraCartaoZAs;
    }

    public void setCompraCartaoZAs(List<CompraCartaoZA> compraCartaoZAs) {
        this.compraCartaoZAs = compraCartaoZAs;
    }
    
    public List<CartaoZonaAzul> getListaCartoes(){
        List<CartaoZonaAzul> listaCartoes = new ArrayList<>();
        for (CompraCartaoZA compraCartaoZA : compraCartaoZAs) {
            listaCartoes.add(compraCartaoZA.getCartaoZonaAzul());
        }
        return listaCartoes;
    }
    public List<Placa> gerListaPlacas(){
        List<Placa> listasPlacas = new ArrayList<>();
        for (CompraCartaoZA compraCartaoZA: compraCartaoZAs) {
            listasPlacas.add(compraCartaoZA.getCartaoZonaAzul().getPlaca());
        }
        return listasPlacas;
    }
    
}
