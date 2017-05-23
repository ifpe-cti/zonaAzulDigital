/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.services;


import com.zonaAzulDigital.entidades.CartaoZonaAzul;
import com.zonaAzulDigital.entidades.Placa;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Samuel
 */
@Path("/cartao")
public class CartaoZonaAzulService {

    @GET
    @Path("/recuperar")
    @Produces(MediaType.APPLICATION_JSON)
    public CartaoZonaAzul getCartao() {

        CartaoZonaAzul c = new CartaoZonaAzul();
       

        Placa p = new Placa();
        p.setLetras("lala");
       

       
       

        BigDecimal dinheiro;

        dinheiro = null;

       

        c.setValor(dinheiro);

        return c;
    }

}
