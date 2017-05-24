/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.services;

import com.google.gson.Gson;
import com.zonaAzulDigital.entidades.Placa;
import javax.swing.JOptionPane;
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
@Path("/placa")
public class PlacaService {
    
    @GET
    @Path("/recuperar")
    @Produces(MediaType.APPLICATION_JSON)
    public Placa recuperaPlaca(){
        Placa p = new Placa();
        
        p.setLetras("alabala");
        p.setNumeros("123");
        
        return p;
    
    }
    
    @POST
    @Path("/salvar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response salvarPlaca(String json){
        
        Gson gson = new Gson();
        
        Placa p = gson.fromJson(json, Placa.class);
        
        Response r = Response.serverError().build();
        
        if (p!=null){
            r = Response.ok().build();
        }
         
        return r;
        
    }
    
    
    
}
