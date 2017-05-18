/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.jersey.services;

import com.zonaAzulDigital.entidades.Carro;
import com.zonaAzulDigital.entidades.Cartao;
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
public class CartaoJerseyService {

    @GET
    @Path("/recuperar")
    @Produces(MediaType.APPLICATION_JSON)
    public Cartao getCartao() {

        Cartao c = new Cartao();
        Carro carro = new Carro();

        Placa p = new Placa();
        p.setLetras("lala");
        p.setNumeros(123);

        carro.setPlaca(p);
        c.setCarro(carro);

        BigDecimal dinheiro;

        dinheiro = null;

        c.setCarro(carro);

        c.setValor(dinheiro);

        return c;
    }

}
