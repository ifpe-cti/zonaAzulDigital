/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.entidades;

import com.zonaAzulDigital.constante.Meses;

/**
 *
 * @author JonasJr
 */
public class VendaMes {

    private Integer mes;
    private Long quantidade;

    public VendaMes(Integer mes, Long quantidade) {
        this.mes = mes;
        this.quantidade = quantidade;
    }

    public Integer getMes() {
        return mes;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public String mesToString() {
        return Meses.values()[mes].name();
    }

}
