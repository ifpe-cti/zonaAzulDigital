/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.services;

import com.google.gson.Gson;
import com.zonaAzulDigital.DAO.DaoCartaoZonaAzulBD;
import com.zonaAzulDigital.DAO.DaoCompraCartaoZADB;
import com.zonaAzulDigital.DAO.DaoMotoristaBD;
import com.zonaAzulDigital.DAO.DaoPlacaBD;

import com.google.gson.GsonBuilder;
import com.zonaAzulDigital.Excecao.LoginException;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.interfaces.ModelCartaoZonaAzulInterface;
import com.zonaAzulDigital.interfaces.ModelMotoristaInterface;
import com.zonaAzulDigital.json.MotoristaDeserializer;
import com.zonaAzulDigital.json.PlacaDeserializer;
import com.zonaAzulDigital.model.ModelCartaoZonaAzul;
import com.zonaAzulDigital.model.ModelMotorista;
import javax.swing.JOptionPane;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Samuel
 */
@Path("/cartaozonaazul")
public class CartaoZonaAzulService {

    @POST
    @Path("/comprar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response comprarCartao(String json) {

        Response r = Response.serverError().build();

        if (json != null && !json.isEmpty()) {


            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Placa.class, new PlacaDeserializer());
            gsonBuilder.registerTypeAdapter(Motorista.class, new MotoristaDeserializer());

            Gson gson = gsonBuilder.create();

            ModelCartaoZonaAzulInterface mcza = new ModelCartaoZonaAzul(new DaoMotoristaBD(), new DaoCartaoZonaAzulBD(),
                    new DaoCompraCartaoZADB(), new DaoPlacaBD());
            ModelMotoristaInterface mc =  new ModelMotorista( new DaoMotoristaBD());
              
            Motorista m = gson.fromJson(json, Motorista.class);
            Placa p = gson.fromJson(json, Placa.class);
            JOptionPane.showMessageDialog(null, m.getCredito());
            try {
                
                mc.login(m.getCpf(), m.getSenha());
                
                mcza.comprar(m, p);
                r = Response.ok().build();

            }
            catch(LoginException le){
                 r = Response.status(401).build();
            }
            catch (Exception e) {
                r = Response.serverError().build();
            }
        }
        return r;
    }
}