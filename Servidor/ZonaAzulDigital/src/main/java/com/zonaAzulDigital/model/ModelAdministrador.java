/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.model;

import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.Excecao.LoginException;
import com.zonaAzulDigital.entidades.Adminstrador;
import com.zonaAzulDigital.interfaces.DaoAdminstrador;
import com.zonaAzulDigital.interfaces.ModelAdministradorInterface;
import java.util.List;

/**
 *
 * @author JonasJr
 */
public class ModelAdministrador implements ModelAdministradorInterface{

    private DaoAdminstrador daoAdminstrador;

    public ModelAdministrador(DaoAdminstrador daoAdminstrador) {
        this.daoAdminstrador = daoAdminstrador;
    }
    
    
    @Override
    public Adminstrador cadastrar(Adminstrador adm) throws DaoException{
        validar(adm);
        adm = daoAdminstrador.cadastrar(adm);
        return adm;
    }

    @Override
    public Adminstrador recuperar(Adminstrador adm)throws DaoException{
        return daoAdminstrador.recuperar(adm.getCpf());
    }

    @Override
    public Adminstrador login(String cpf, String senha) throws DaoException, LoginException{
        return daoAdminstrador.login(cpf, senha);
    }

    @Override
    public List<Adminstrador> listarTudo() {
        return daoAdminstrador.listarTodos();
    }
    
    public boolean validar(Adminstrador adm) {
        
        return  true;
    }
    
}
