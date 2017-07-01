/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.tests.DAO;

import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.interfaces.DAOPlaca;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JonasJr
 */
public class DAOPlacaFake implements DAOPlaca{
    private List<Placa> placas;

    public DAOPlacaFake() {
        placas = new ArrayList<>();
    }
    
    

    @Override
    public Placa cadastrar(Placa objeto) throws DaoException {
        objeto.setId(placas.size() + 1);
        placas.add(objeto);
        return objeto;
    }

    @Override
    public Placa recuperar(String letras, String numeros) throws DaoException {
        Placa returno = null;
        for (Placa placa : placas) {
            if (placa.getLetras().equals(letras) && placa.getNumeros().equals(numeros)) {
                returno = placa;
            }
        }
        return returno;
    }

    @Override
    public Placa recuperarPorId(int id) throws DaoException {
        Placa returno = null;
        for (Placa placa : placas) {
            if (placa.getId() == id) {
                returno = placa;
            }
        }
        return returno;
    }

    @Override
    public List<Placa> listarTudo() {
        return placas;
    }
    
}
