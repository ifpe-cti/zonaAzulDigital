/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.entidades;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JonasJr
 */
public class MotoristaTest {
    
    public MotoristaTest() {
    }

    
    /**
     * Teste de Debitar()
     * 
     * Um motorista com credito igual a 10
     * Debitar: 2 
     * Esperado: True 
     * 
     * Um motorista com credito igual a 2
     * Debitar: 2 
     * Esperado: True 
     * 
     * Um motorista com credito igual a 1.99
     * Debitar: 2 
     * Esperado: False
     * 
     * Um motorista com credito igual a 0
     * Debitar: 2 
     * Esperado: False
     * 
     * Um motorista com credito igual a 2
     * Debitar: -2 
     * Esperado: False
     * 
     * Um motorista com credito igual a 0
     * Debitar: -2 
     * Esperado: False 
     * 
     * Um motorista com credito igual a 2
     * Debitar: 1.8 
     * Esperado: True 
     */
 
    /**
     * Testes de Creditar()
     * 
     * Um motorista com credito igual a 0
     * Creditar: 2 
     * Esperado: True 
     * 
     * Um motorista com credito igual a 2
     * Creditar: 2 
     * Esperado: True
     * 
     * Um motorista com credito igual a 2
     * Creditar: -2 
     * Esperado: False
     * 
     * Um motorista com credito igual a 0
     * Creditar: -2 
     * Esperado: False
     */
 
    
}
