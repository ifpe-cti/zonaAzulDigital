/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.tests.DAO.base;

import com.zonaAzulDigital.entidades.CompraCartaoZA;
import com.zonaAzulDigital.entidades.Motorista;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JonasJr
 */
public class Compras {

    private List<CompraCartaoZA> comprasCartaoZAs;

    public Compras(Motoristas motoristas, Cartoes cartoes) {
        comprasCartaoZAs = new ArrayList<>();
        comprasCartaoZAs.add(new CompraCartaoZA(motoristas.get(0), cartoes.get(0)));
        comprasCartaoZAs.add(new CompraCartaoZA(motoristas.get(1), cartoes.get(1)));
        comprasCartaoZAs.add(new CompraCartaoZA(motoristas.get(2), cartoes.get(2)));
    }

    public List<CompraCartaoZA> comprasCartaoAZs() {
        return comprasCartaoZAs;
    }

    public CompraCartaoZA get(int i) {
        return comprasCartaoZAs.get(i);
    }

}
