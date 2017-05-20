/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Samuel
 */
public class Cartao {

    private Placa placa;
    private LocalDate data;
    private BigDecimal valor;

    public Placa getCarro() {
        return placa;
    }

    public void setCarro(Placa placa) {
        this.placa = placa;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

}
