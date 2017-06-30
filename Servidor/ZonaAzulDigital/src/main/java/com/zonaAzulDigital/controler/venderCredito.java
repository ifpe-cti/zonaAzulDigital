/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.controler;

import com.zonaAzulDigital.DAO.DaoMotoristaBD;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.model.ModelMotorista;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author JonasJr
 */
@ManagedBean
@ViewScoped
public class venderCredito {
    private Motorista motorista;
    private double valor;
    private ModelMotorista modelMotorista;

    public venderCredito() {
        this.motorista = new Motorista();
        this.valor = 0;
        this.modelMotorista = new ModelMotorista(new DaoMotoristaBD());
    }

    
    public boolean comprar(){
        return false;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    public List<Motorista> listarTodosMotorista(){
        return modelMotorista.listarTodos();
    }
    
}
 