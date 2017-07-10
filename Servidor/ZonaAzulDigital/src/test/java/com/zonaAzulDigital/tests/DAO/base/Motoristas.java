/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.tests.DAO.base;

import com.zonaAzulDigital.entidades.Motorista;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JonasJr
 */
public class Motoristas {
    private List<Motorista> motoristas;

    public Motoristas() {
        motoristas = new ArrayList<>();
        motoristas.add(new Motorista(1,"Jonas Ferreira Leal Junior", "10654901430",new BigDecimal(0), "123"));
        motoristas.add(new Motorista(2,"Jonas Ferreira Leal", "19167482449",new BigDecimal(0), "123"));
        motoristas.add(new Motorista(3,"Carlos Eduardo", "04982857407",new BigDecimal(0), "123"));
    }
    
    public List<Motorista> getMotoristas(){
        return motoristas;
    }
    
    public Motorista get(int i){
        return motoristas.get(i);
    }
}
