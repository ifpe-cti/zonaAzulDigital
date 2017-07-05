/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.model;

import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.Excecao.PlacaException;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.interfaces.DAOPlaca;
import com.zonaAzulDigital.interfaces.ModelPlacaInterface;

/**
 *
 * @author JonasJr
 */
public class ModelPlaca implements ModelPlacaInterface {

    private DAOPlaca daoPlaca;

    public ModelPlaca(DAOPlaca daoPlaca) {
        this.daoPlaca = daoPlaca;
    }

    @Override
    public Placa cadastrar(Placa objeto) throws DaoException {

        objeto = (Placa) this.daoPlaca.cadastrar(objeto);
        return objeto;
    }

    @Override
    public Placa recuperar(Placa placa) throws DaoException {

        placa = (Placa) this.daoPlaca.recuperar(placa.getLetras(), placa.getNumeros());

        return placa;
    }
    
    @Override
    public boolean validar(Placa p) throws PlacaException {
        p.setLetras(p.getLetras().toUpperCase());
        
        if (p == null) {
            throw new PlacaException(PlacaException.NULL);
        }
        if (p.getLetras().length() != 3) {
            throw new PlacaException(PlacaException.LETRAS);
        }
        if (p.getNumeros().length() != 4) {
            throw new PlacaException(PlacaException.NUMEROS);
        }
        for (int i = 0; i < p.getLetras().length(); i++) {
            char c = p.getLetras().charAt(i);
            if ( c < 'A' || c > 'Z') {
                throw new PlacaException(PlacaException.LETRAS);
            }
        }
        for(int i = 0 ; i< p.getNumeros().length();i++){
            char num = p.getNumeros().charAt(i);
            if(num < '0' || num > '9'){
                throw new PlacaException(PlacaException.NUMEROS);
            }
        }
        
        return true;
    }

    @Override
    public Placa recuperarId(int id) throws DaoException {

        return this.daoPlaca.recuperarPorId(id);
    }

}
