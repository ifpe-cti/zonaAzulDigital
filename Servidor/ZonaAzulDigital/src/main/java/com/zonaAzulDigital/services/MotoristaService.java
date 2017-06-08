/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.services;

import com.zonaAzulDigital.model.Model;
import com.google.gson.Gson;
import com.zonaAzulDigital.Excecao.CpfException;
import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.interfaces.ModelMotoristaInterface;
import com.zonaAzulDigital.model.ModelMotorista;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Samuel
 */
@Path("/motorista")
public class MotoristaService {

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response fazerLogin(String json) {

        Response r = null;

        if (json != null && !json.isEmpty()) {
            Gson gson = new Gson();

            ModelMotorista md = new ModelMotorista();
            Motorista m = gson.fromJson(json, Motorista.class);

            try {

                //md.login(m.getCpf(),m.getSenha());
                r = Response.ok().build();

            } catch (Exception e) {
                r = Response.serverError().build();
            }

        }

        return r;
    }

    @POST
    @Path("/salvar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response salvar(String json) {

        Response r = null;

        if (json != null && !json.isEmpty()) {
            Gson gson = new Gson();

            ModelMotoristaInterface md = new ModelMotorista();

            Motorista m = gson.fromJson(json, Motorista.class);

            try {

                md.cadastrar(m);
                r = Response.ok().build();

            } catch (DaoException de) {
                r = Response.serverError().build();
            }catch(CpfException ce){
                r = Response.serverError().build();
            }

        }

        return r;
    }
}
