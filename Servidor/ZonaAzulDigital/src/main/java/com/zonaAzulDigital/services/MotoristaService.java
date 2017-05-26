/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.services;

import com.google.gson.Gson;
import com.zonaAzulDigital.entidades.Motorista;
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

    @GET
    @Path("/recuperar")
    @Produces(MediaType.APPLICATION_JSON)
    public Motorista getMotorista() {
        Motorista motorista = new Motorista();

        motorista.setNome("Samuel");

        return motorista;
    }

    @POST
    @Path("/salvar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response salvarMotorista(String json) {

        Response r = Response.serverError().build();

        if (json != null && !json.isEmpty()) {
            Gson gson = new Gson();
            
            ModelMotorista md = new ModelMotorista();

            Motorista m = gson.fromJson(json, Motorista.class);

            try {
               
                md.cadastrar(m);
                r = Response.ok().build();
                
            } catch (Exception e) {

            }

        }

        return r;
    }
}
