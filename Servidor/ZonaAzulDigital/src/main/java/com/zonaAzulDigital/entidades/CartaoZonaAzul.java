/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class CartaoZonaAzul implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int numero;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Placa placa;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEntacionamento;
    private BigDecimal valor;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataExpirar;
    @Transient
    private String tempoRestante;

    public CartaoZonaAzul() {
    }

    public CartaoZonaAzul(Placa placa) {
        this.placa = placa;
        this.dataEntacionamento = new Date();
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
    
    public Date getData() {
        return dataEntacionamento;
    }

    public void setData(Date data) {
        this.dataEntacionamento = data;
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

    public Date getDataEntacionamento() {
        return dataEntacionamento;
    }

    public void setDataEntacionamento(Date dataEntacionamento) {
        this.dataEntacionamento = dataEntacionamento;
    }

    public Date getDataExpirar() {
        return dataExpirar;
    }

    public void setDataExpirar(Date dataExpirar) {
        this.dataExpirar = dataExpirar;
    }
    
    
}
