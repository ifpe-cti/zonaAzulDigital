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
public class DaoException extends Exception{

    public static final String NAOCADASTRADO = "Não foi possivel cadastrar";
    public static final String NAOATUALIZADO = "Não foi possivel atualizar";
    public static final String NAODELETADO = "Não foi possivel deletar";
    public DaoException(String msg) {
        super(msg);
    }
    
}
