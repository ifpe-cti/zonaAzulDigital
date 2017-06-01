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
public class LoginException extends Exception{
    public static String FALHOU = "Login falhou";
    
    public LoginException(String msg) {
        super(msg);
    }
    
}
