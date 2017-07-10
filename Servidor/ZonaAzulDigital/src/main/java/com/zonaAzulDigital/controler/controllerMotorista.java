/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.controler;

import com.zonaAzulDigital.Criptografia.MD5Crip;
import com.zonaAzulDigital.DAO.DaoMotoristaBD;
import com.zonaAzulDigital.Excecao.CpfException;
import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.Excecao.MotoristaException;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.interfaces.ModelMotoristaInterface;
import com.zonaAzulDigital.model.ModelMotorista;
import java.io.Serializable;
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
public class controllerMotorista implements Serializable {

    private ModelMotoristaInterface modelMotorista;
    private Motorista motorista;

    public controllerMotorista() {
        this.modelMotorista = new ModelMotorista(new DaoMotoristaBD());
        this.motorista = new Motorista();
    }

    public void cadastrar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            String senhaCrip = MD5Crip.encriptar(motorista.getSenha());
            motorista.setSenha(senhaCrip);
            motorista = this.modelMotorista.cadastrar(motorista);
            context.addMessage(null, new FacesMessage("Sucesso", motorista.getNome() + " foi cadastrado com sucesso!"));

        } catch (DaoException | CpfException | MotoristaException ex) {
            context.addMessage(null, new FacesMessage("Erro", ex.getMessage()));
            Logger.getLogger(controllerMotorista.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

}
