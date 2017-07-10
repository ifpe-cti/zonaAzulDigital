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
public class CartaoZAException extends Exception {

    public static final String NAOCADASTRADO = "Não foi possivel cadastrar";
    public static final String NAOATUALIZADO = "Não foi possivel atualizar";
    public static final String NAODELETADO = "Não foi possivel deletar";
    public static final String CIDADENULL = "Cidade é obrigatoria";
    public static final String PRECO = "Preço é obrigatorio";
    public static final String NULL = "Não pode ser nulo";

    public CartaoZAException(String message) {
        super(message);
    }

    public CartaoZAException(String msg, Throwable causa) {
        super(msg, causa);
    }

}
