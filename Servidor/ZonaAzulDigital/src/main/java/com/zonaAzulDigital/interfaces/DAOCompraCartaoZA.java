/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.interfaces;

import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.entidades.CompraCartaoZA;
import com.zonaAzulDigital.entidades.Motorista;
import java.util.List;

/**
 *
 * @author JonasJr
 */
public interface DAOCompraCartaoZA {
    public CompraCartaoZA comprar(CompraCartaoZA compraCartaoZA)throws DaoException;
    public CompraCartaoZA recuperarPor(int id);
    public List<CompraCartaoZA> recuperarPor(Motorista motorista);
    
}
