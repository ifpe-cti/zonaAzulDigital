/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.services;


import com.zonaAzulDigital.entidades.Motorista;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Samuel
 */
@Path("/motorista")
public class MotoristaService {
    
    
    @GET
    @Path("/recuperar")
    @Produces(MediaType.APPLICATION_JSON)
    public Motorista getMotorista(){
        Motorista motorista =  new Motorista();
        
        motorista.setNome("Samuel");
        
        return motorista;
    }
    
    
    
}
