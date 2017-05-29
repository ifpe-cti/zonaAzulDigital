/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.Excecao;

/**
 *
 * @author JonasJr
 */
public enum PlacaException {
    NULL("Placa é obrigatorio"),
    LETRAS("Placa deve conter 3 letras"),
    NUMEROS("Placa deve conter 4 números"),
    NAOENCONTRADA("Placa não encontrada");
    
    public String msg;

    private PlacaException(String msg) {
        this.msg = msg;
    }
    
}
