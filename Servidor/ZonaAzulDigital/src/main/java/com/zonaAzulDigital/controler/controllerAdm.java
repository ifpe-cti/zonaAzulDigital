/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.controler;

import com.zonaAzulDigital.DAO.DaoAdministradorDB;
import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.Excecao.LoginException;
import com.zonaAzulDigital.entidades.Adminstrador;
import com.zonaAzulDigital.model.ModelAdministrador;
import java.io.IOException;
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
public class controllerAdm implements Serializable {

    private ModelAdministrador modelAdministrador;
    private Adminstrador administrador;

    public controllerAdm() {
        this.modelAdministrador = new ModelAdministrador(new DaoAdministradorDB());
        this.administrador = new Adminstrador();
    }

    public Adminstrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Adminstrador administrador) {
        this.administrador = administrador;
    }

    public void cadastrar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            modelAdministrador.cadastrar(administrador);
            context.addMessage(null, new FacesMessage("Cadastrado com sucesso!"));
        } catch (DaoException ex) {
            context.addMessage(null, new FacesMessage("Erro:", ex.getMessage()));
            Logger.getLogger(controllerAdm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void login() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            Adminstrador adm = modelAdministrador.login(administrador.getCpf(), administrador.getSenha());
            context.getExternalContext().getSessionMap().put("admLogado", adm);
            context.getExternalContext().redirect(
                    context.getExternalContext().getRequestContextPath()+
                            "/administrativo/index.xhtml");
        } catch (DaoException | LoginException ex) {
            context.addMessage(null, new FacesMessage("Erro", ex.getMessage()));
            Logger.getLogger(controllerAdm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(controllerAdm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("admLogado");
        try {
            context.getExternalContext().redirect(
                    context.getExternalContext().getRequestContextPath()+
                            "/administrativo/index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(controllerAdm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Adminstrador getLogado(){
        return (Adminstrador) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("admLogado");
    }
    
    public boolean estaLogado(){
        boolean retorno = false;
        if(getLogado() != null ){
            retorno = true;
        }
        return retorno;
    }

}
