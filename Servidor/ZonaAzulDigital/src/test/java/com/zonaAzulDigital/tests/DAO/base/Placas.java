/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.tests.DAO.base;

import com.zonaAzulDigital.entidades.Placa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JonasJr
 */
public class Placas {
    private List<Placa> placas;

    public Placas() {
        placas = new ArrayList<>();
        placas.add(new Placa((placas.size() + 1), "kfw", "8983"));
        placas.add(new Placa((placas.size() + 1), "mus", "2277"));
        placas.add(new Placa((placas.size() + 1), "khx", "0066"));
    }

    public List<Placa> getPlacas() {
        return placas;
    }
    
    public  Placa get(int i ){
        return placas.get(i);
    }
    
    
}
