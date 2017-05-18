/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.jersey.services;

import com.zonaAzulDigital.entidades.Guarda;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Samuel
 */
@Path("/guarda")
public class GuardaJerseyService {

    @GET
    @Path("/recuperar")
    @Produces(MediaType.APPLICATION_JSON)
    public Guarda getGuarda() {
        Guarda g = new Guarda();

        g.setCpf("3492");
        g.setNome("Samuel");
        g.setSenha("123");

        return g;
    }

}
