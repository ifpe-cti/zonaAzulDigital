/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.model.DAO;

import java.util.List;


/**
 *
 * @author JonasJr
 */
public interface DAO <T>{
    
    T cadastrar(T placa);
    T atualizar(T placa);
    T recuperarPorId(int id);
    T deletar(T placa);
    List<T> listarTudo();
    
}
