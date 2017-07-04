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
public class PlacaException extends Exception{
    public static final String NULL="Placa é obrigatorio";
    public static final String LETRAS = "Placa deve conter 3 letras" ;
    public static final String NUMEROS = "Placa deve conter 4 números";
    public static final String NAOENCONTRADA = "Placa não encontrada";
    
     public PlacaException(String msg) {
        super(msg);
    }

    public PlacaException(String msg, Throwable causa) {
        super(msg, causa);
    }
    
}
