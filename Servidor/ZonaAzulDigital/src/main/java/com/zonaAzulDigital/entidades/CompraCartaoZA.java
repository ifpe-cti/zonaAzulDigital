/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.entidades;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author JonasJr
 */
@Entity
public class CompraCartaoZA implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Motorista motorista;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private CartaoZonaAzul cartaoZonaAzul;

    public CompraCartaoZA() {
    }

    public CompraCartaoZA(Motorista motorista, CartaoZonaAzul cartaoZonaAzul) {
        this.motorista = motorista;
        this.cartaoZonaAzul = cartaoZonaAzul;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public CartaoZonaAzul getCartaoZonaAzul() {
        return cartaoZonaAzul;
    }

    public void setCartaoZonaAzul(CartaoZonaAzul cartaoZonaAzul) {
        this.cartaoZonaAzul = cartaoZonaAzul;
    }

}
