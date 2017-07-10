/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.Criptografia;

import com.zonaAzulDigital.controler.controllerRelatorio;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JonasJr
 */
public class MD5Crip {
    
    public static String encriptar(String senha){
        String retorno = "";
        MessageDigest ms;
        try{
            ms = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(ms.digest(senha.getBytes()));
            retorno = hash.toString(32);
        }catch(Exception ex){
            Logger.getLogger(MD5Crip.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
}
