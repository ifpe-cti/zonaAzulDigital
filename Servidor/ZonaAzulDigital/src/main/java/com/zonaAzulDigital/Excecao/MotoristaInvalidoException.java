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
public class MotoristaInvalidoException extends Exception{
    
    public static final String NULL = "Motorista é obrigatorio";
    public static final String NOME = "Nome é obrigatorio";
    public static final String CPF = "CPF é obrigatorio";
    
    public MotoristaInvalidoException(String msg) {
        super(msg);
    }
    
}
