/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Samuel
 */
@Entity
public class CartaoZonaAzul implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int numero;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Placa placa;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicio;
    private BigDecimal valor;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFim;
    @Transient
    private String tempoRestante;

    public CartaoZonaAzul() {
    }

    public CartaoZonaAzul(Placa placa) {
        this.placa = placa;
        this.dataInicio = new Date();
        LocalDateTime ltd = LocalDateTime.now().plusHours(2);
        this.dataFim = Date.from(ltd.atZone(ZoneId.systemDefault()).toInstant());
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Placa getPlaca() {
        return placa;
    }

    public void setPlaca(Placa placa) {
        this.placa = placa;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getTempoRestante() {
        return tempoRestante;
    }

    public void setTempoRestante(String tempoRestante) {
        this.tempoRestante = tempoRestante;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

}
