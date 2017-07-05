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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author JonasJr
 */

@ManagedBean
@ViewScoped
public class controllerAdministrativo {
    
    private ModelAdministrador modelAdministrador;
    private Adminstrador administrador;
    
    public controllerAdministrativo(){
        this.modelAdministrador = new ModelAdministrador(new DaoAdministradorDB());
    }
    public void fazerLogin(String cpf,String senha){
        try{
            
            modelAdministrador.login(cpf, senha);
            
        }catch(DaoException|LoginException ex){
            
        }
        
    }
            
            
}
