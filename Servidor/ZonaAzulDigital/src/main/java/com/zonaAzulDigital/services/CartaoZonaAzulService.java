/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.services;

import com.google.gson.Gson;
import com.zonaAzulDigital.entidades.CartaoZonaAzul;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.model.ModelCartaoZonaAzul;
import java.math.BigDecimal;
import java.time.LocalDate;
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
@Path("/cartaozonaazul")
public class CartaoZonaAzulService {

    @GET
    @Path("/recuperar")
    @Produces(MediaType.APPLICATION_JSON)
    public CartaoZonaAzul getCartao() {

        CartaoZonaAzul c = new CartaoZonaAzul();

        return c;
    }

    @POST
    @Path("/salvar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response salvarCartao(String json) {

        Response r = Response.serverError().build();

        if (json != null && !json.isEmpty()) {

            Gson gson = new Gson();

            ModelCartaoZonaAzul mc = new ModelCartaoZonaAzul();

            CartaoZonaAzul c = gson.fromJson(json, CartaoZonaAzul.class);

            try {

                mc.comprar(null,null);
                r = Response.ok().build();

            } catch (Exception e) {

            }
        }
        return r;
    }

}
