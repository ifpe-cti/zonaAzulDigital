/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.controler;

import com.zonaAzulDigital.DAO.DaoCartaoZonaAzulBD;
import com.zonaAzulDigital.DAO.DaoCompraCartaoZADB;
import com.zonaAzulDigital.DAO.DaoMotoristaBD;
import com.zonaAzulDigital.DAO.DaoPlacaBD;
import com.zonaAzulDigital.Excecao.CartaoZAException;
import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.entidades.CartaoZonaAzulInfo;
import com.zonaAzulDigital.interfaces.ModelCartaoZonaAzulInterface;
import com.zonaAzulDigital.model.ModelCartaoZonaAzul;
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
public class controllerCidade implements Serializable {

    private CartaoZonaAzulInfo caInfo;
    private ModelCartaoZonaAzulInterface modelCartaoZonaAzul;

    public controllerCidade() {
        this.caInfo = new CartaoZonaAzulInfo();
        this.caInfo.setCidade("Garanhuns");
        this.modelCartaoZonaAzul = new ModelCartaoZonaAzul(new DaoMotoristaBD(), new DaoCartaoZonaAzulBD(),
                new DaoCompraCartaoZADB(), new DaoPlacaBD());

    }

    public CartaoZonaAzulInfo getCaInfo() {
        return caInfo;
    }

    public void setCaInfo(CartaoZonaAzulInfo caInfo) {
        this.caInfo = caInfo;
    }

    public void cadastrar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            modelCartaoZonaAzul.cadastrarDetalhes(caInfo);
            context.addMessage(null, new FacesMessage("Cadastrado com sucesso"));
        } catch (CartaoZAException | DaoException ex) {
            context.addMessage(null, new FacesMessage(ex.getMessage()));
            Logger.getLogger(controllerCidade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(ex.getMessage()));
            Logger.getLogger(controllerCidade.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

}
