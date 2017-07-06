/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author JonasJr
 */
@Entity
public class CartaoZonaAzulInfo implements Serializable {

    @Id
    private String cidade;
    private BigDecimal preco;

    public CartaoZonaAzulInfo() {
    }

    public CartaoZonaAzulInfo(String cidade, BigDecimal preco) {
        this.cidade = cidade;
        this.preco = preco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

}
