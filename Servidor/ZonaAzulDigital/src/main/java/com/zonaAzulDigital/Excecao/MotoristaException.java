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
public class MotoristaException extends Exception{
    public static final String NULL = "Motorista é obrigatorio";
    public static final String NOMEOBRIGATORIO = "Nome é obrigatorio";
    public static final String CPFOBRIGATORIO = "CPF é obrigatorio";
    public static final String NAOENCONTRADO = "Motorista não foi encontrado";
    
    public MotoristaException(String msg) {
        super(msg);
    }
    
}
