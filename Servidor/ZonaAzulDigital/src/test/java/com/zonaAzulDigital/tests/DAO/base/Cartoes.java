/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.tests.DAO.base;

import com.zonaAzulDigital.entidades.CartaoZonaAzul;
import com.zonaAzulDigital.entidades.Placa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JonasJr
 */
public class Cartoes {

    private List<CartaoZonaAzul> cartoes;

    public Cartoes(Placas placas) {
        cartoes = new ArrayList<>();
        CartaoZonaAzul ca = new CartaoZonaAzul(placas.get(0));
        ca.setNumero(1);
        cartoes.add(ca);
        ca = new CartaoZonaAzul(placas.get(1));
        ca.setNumero(2);
        cartoes.add(ca);
        ca = new CartaoZonaAzul(placas.get(2));
        ca.setNumero(3);
        cartoes.add(ca);
    }

    public List<CartaoZonaAzul> getCartoes() {
        return cartoes;
    }

    public CartaoZonaAzul get(int i) {
        return cartoes.get(i);
    }

}
