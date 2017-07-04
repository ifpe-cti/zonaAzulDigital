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
public class MotoristaException extends Exception {

    public static final String NULL = "Motorista é obrigatorio";
    public static final String NOMEOBRIGATORIO = "Nome é obrigatorio";
    public static final String NAOENCONTRADO = "Motorista não foi encontrado";
    public static final String CREDITOINSUFICIENTE = "Creditos insuficiente";
    public static final String SENHAOBRIGATORIA = "Senha é obrigatoria";
    public static final String CREDITOINVALIDO = "Crédito Invalido";

    public MotoristaException(String msg) {
        super(msg);
    }

    public MotoristaException(String message, Throwable cause) {
        super(message, cause);
    }
    
    

}
