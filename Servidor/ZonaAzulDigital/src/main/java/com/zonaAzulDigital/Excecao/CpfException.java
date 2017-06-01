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
public class CpfException extends Exception{
    public static String NULL ="CPF é obrigatorio";
    public static String TAMANHO = "CPF deve conter 11 números";
    public static String NAOENCONTRADA = "CPF não encontrada";
    public static String NAOVALIDO = "CPF não é valido.";
    

    public CpfException(String msg) {
        super(msg);
    }

    public CpfException(String msg, Throwable causa) {
        super(msg, causa);
    }
    
}
