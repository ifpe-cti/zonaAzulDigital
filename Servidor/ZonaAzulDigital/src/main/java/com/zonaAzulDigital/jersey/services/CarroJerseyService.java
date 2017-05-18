/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.jersey.services;

import com.zonaAzulDigital.entidades.Carro;
import com.zonaAzulDigital.entidades.Placa;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Samuel
 */
@Path("/carro")
public class CarroJerseyService {
    
    @GET
    @Path("/recuperar")
    @Produces(MediaType.APPLICATION_JSON)
    public Carro getCarro(){
        Carro c = new Carro();
        Placa p = new Placa();
        
        p.setLetras("Jonas");
        p.setNumeros(2424);
        
        c.setPlaca(p);
        
        
        return c;
        
        
    }
    
    
    
}
