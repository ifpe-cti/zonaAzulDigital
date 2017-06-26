/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.controler;

import com.zonaAzulDigital.DAO.DaoMotoristaBD;
import com.zonaAzulDigital.Excecao.CpfException;
import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.interfaces.ModelMotoristaInterface;
import com.zonaAzulDigital.model.ModelMotorista;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author JonasJr
 */
@ManagedBean
@ViewScoped
public class cadastrarMotorista {

    private ModelMotoristaInterface modelMotorista;
    private Motorista motorista;

    public cadastrarMotorista() {
        this.modelMotorista = new ModelMotorista(new DaoMotoristaBD());
        this.motorista = new Motorista();
    }

    public void cadastrar() {
        
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            motorista = this.modelMotorista.cadastrar(motorista);
            context.addMessage(null, new FacesMessage("Sucesso", motorista.getNome() + " foi cadastrado com sucesso!"));

        } catch (DaoException | CpfException ex) {
            Logger.getLogger(cadastrarMotorista.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

}
