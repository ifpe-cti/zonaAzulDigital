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
import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.Excecao.LoginException;
import com.zonaAzulDigital.Excecao.MotoristaException;
import com.zonaAzulDigital.Excecao.PlacaException;
import com.zonaAzulDigital.entidades.CartaoZonaAzul;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.interfaces.DAOMotorista;
import com.zonaAzulDigital.interfaces.ModelCartaoZonaAzulInterface;
import com.zonaAzulDigital.interfaces.ModelMotoristaInterface;
import com.zonaAzulDigital.json.MotoristaDeserializer;
import com.zonaAzulDigital.json.PlacaDeserializer;
import com.zonaAzulDigital.model.ModelCartaoZonaAzul;
import com.zonaAzulDigital.model.ModelMotorista;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
            
            try {    
                
                Motorista m = gson.fromJson(json, Motorista.class);
                Placa p = gson.fromJson(json, Placa.class);

                
                Motorista motorista = mc.login(m.getCpf(), m.getSenha());
                
                mcza.comprar(motorista, p);
                
                Motorista motoristaRetorno = mc.login(m.getCpf(), m.getSenha());
               
                motoristaRetorno.setSenha("");
                String jsonRetorno  = gson.toJson(motoristaRetorno);
                
                r = Response.ok(jsonRetorno).build();
            }
            catch(PlacaException pe){
            r = Response.status(403).build();
            Logger.getLogger(CartaoZonaAzulService.class.getName()).log(Level.SEVERE, null, pe);
            }
            catch(MotoristaException me ){
                r = Response.status(406).build();
                Logger.getLogger(CartaoZonaAzulService.class.getName()).log(Level.SEVERE, null, me);
            }
            catch(LoginException le){
                 r = Response.status(401).build();
                 Logger.getLogger(CartaoZonaAzulService.class.getName()).log(Level.SEVERE, null, le);
            }
            catch (Exception e) {
                r = Response.serverError().build();
                Logger.getLogger(CartaoZonaAzulService.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return r;
    }
    
    @GET
    @Path("/buscar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response consultarCartao(String json){
        
        Response r = Response.serverError().build();
        
        if(json != null || !json.isEmpty()){
            
            GsonBuilder gsonBuilder = new GsonBuilder();
            
            gsonBuilder.registerTypeAdapter(Placa.class, new PlacaDeserializer());
            
            Gson gson = gsonBuilder.create();
            
            ModelCartaoZonaAzulInterface mcza = new ModelCartaoZonaAzul(new DaoMotoristaBD(), new DaoCartaoZonaAzulBD(), new DaoCompraCartaoZADB(), new DaoPlacaBD()); 
            
            Placa p = gson.fromJson(json, Placa.class);
            
            try{
                
                CartaoZonaAzul cza = mcza.recuperaCartaoAtivo(p);
                
                
                String jsonRetorno = gson.toJson(cza);
                
                r = Response.ok(jsonRetorno).build();

            }catch(DaoException de){
                r = Response.status(417).build(); 
                Logger.getLogger(CartaoZonaAzulService.class.getName()).log(Level.SEVERE, null, de);
            }
            catch(PlacaException pe){
                r = Response.status(403).build(); 
                Logger.getLogger(CartaoZonaAzulService.class.getName()).log(Level.SEVERE, null, pe);
            }
            catch(Exception e){
                r = Response.serverError().build();
                Logger.getLogger(CartaoZonaAzulService.class.getName()).log(Level.SEVERE, null, e);
            }
            
        }
        
        return r;
    }
    
    
    @POST
    @Path("/cartoesativos")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response consultaCartoesAtivos(String json) {

        Response r = Response.serverError().build();

        if (json != null && !json.isEmpty()) {

            Gson gson = new Gson();

            ModelCartaoZonaAzulInterface mcza = new ModelCartaoZonaAzul(new DaoMotoristaBD(), new DaoCartaoZonaAzulBD(),
                    new DaoCompraCartaoZADB(), new DaoPlacaBD());
            try {    
                
                Motorista m = gson.fromJson(json, Motorista.class);
                List<CartaoZonaAzul> listaCartoesAtivos = mcza.CartoesAtivosPor(m);
                
                String jsonRetorno = gson.toJson(listaCartoesAtivos);
                
                r = Response.ok(jsonRetorno).build();
            }
            catch (Exception e) {
                r = Response.serverError().build();
                Logger.getLogger(CartaoZonaAzulService.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return r;
    }
    
    @POST
    @Path("/relatorioscartoes")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response consultaTodosCartoesComprados(String json) {

        Response r = Response.serverError().build();

        if (json != null && !json.isEmpty()) {

            Gson gson = new Gson();

            ModelCartaoZonaAzulInterface mcza = new ModelCartaoZonaAzul(new DaoMotoristaBD(), new DaoCartaoZonaAzulBD(),
                    new DaoCompraCartaoZADB(), new DaoPlacaBD());
            try {    
                
                Motorista m = gson.fromJson(json, Motorista.class);
                List<CartaoZonaAzul> listaCartoesAtivos = mcza.recuperarTodosCartoesPor(m);
                
                String jsonRetorno = gson.toJson(listaCartoesAtivos);
                
                r = Response.ok(jsonRetorno).build();
            }
            catch (Exception e) {
                r = Response.serverError().build();
                Logger.getLogger(CartaoZonaAzulService.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return r;
    }
    
}