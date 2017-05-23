/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.model;

/**
 *
 * @author JonasJr
 */
public interface Model<T> {
    T cadastrar(T objeto);
    T atualizar(T objeto);
    T recuperar(T objeto);
    T deletar(T objeto);
}
