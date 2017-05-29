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
public enum MotoristaException {
    NULL("Motorista é obrigatorio"),
    CPF("CPF é obrigatorio"),
    NAOENCONTRADA("Motorista não encontrada");
    
    public String msg;

    private MotoristaException(String msg) {
        this.msg = msg;
    }
    
}
