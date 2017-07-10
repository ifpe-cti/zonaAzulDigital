/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.controler;

import com.zonaAzulDigital.DAO.DaoMotoristaBD;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.model.ModelMotorista;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author JonasJr
 */
@ManagedBean
@ViewScoped
public class venderCredito implements Serializable {

    private Motorista motorista;
    private double valor;
    private ModelMotorista modelMotorista;
    private boolean realizadoSucesso;

    public venderCredito() {
        this.motorista = new Motorista();
        this.valor = 0;
        this.modelMotorista = new ModelMotorista(new DaoMotoristaBD());
        this.realizadoSucesso = false;
    }

    public boolean comcluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        boolean resultado = false;
        try {
            motorista.creditar(new BigDecimal(valor));
            modelMotorista.atualizar(motorista);
            context.addMessage(null, new FacesMessage("Opeção realizada"));
            resultado = true;
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(e.getMessage()));
        }
        return resultado;

    }

    public String proximo(FlowEvent evento) {
        String passo = evento.getOldStep();
        if (this.realizadoSucesso) {
            this.motorista = new Motorista();
            this.valor = 0;
            this.realizadoSucesso = false;
            passo = "motorista";
        } else if (evento.getNewStep().equalsIgnoreCase("valorCredito")) {
            if (motorista != null && motorista.getCpf() != null && !motorista.getCpf().isEmpty()) {
                passo = evento.getNewStep();
            }
        } else if (evento.getNewStep().equalsIgnoreCase("confirmacao")) {
            if (valor > 0) {
                passo = evento.getNewStep();
            }
        } else if (evento.getNewStep().equalsIgnoreCase("resultado")) {
            this.realizadoSucesso = this.comcluir();
            passo = evento.getNewStep();
        } else {
            passo = evento.getNewStep();
        }
        return passo;
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

    public List<Motorista> listarTodosMotorista() {
        return modelMotorista.listarTodos();
    }

}
