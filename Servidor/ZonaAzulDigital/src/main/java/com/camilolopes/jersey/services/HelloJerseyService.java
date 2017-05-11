/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.camilolopes.jersey.services;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Samuel
 */
@Path("/hello")
public class HelloJerseyService {
    
    
    @GET
    @Path("/client")
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente getCliente(){
        Cliente cliente =  new Cliente();
        
        cliente.setNome("Samuel");
        
        return cliente;
    }
    
    
    
}
