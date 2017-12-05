/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.model.ModelMotorista;
import com.zonaAzulDigital.Excecao.MotoristaException;
/**
 *
 * @author LUCINALDO
 */
import java.math.BigDecimal;
public class ModelMotoristaTest {
    
    Motorista motorista;
    Motorista motorista2;
    Motorista motorista3;
    Motorista motorista4;
    Motorista motorista5;
    Motorista motorista6;
    Motorista motorista7;
    
    @Test(expected = MotoristaException.class)
    public void testValidarMotoristaNull(){
        motorista = null;
        ModelMotorista.validar(motorista);
    }
    
    @Test(expected = MotoristaException.class)
    public void testValidarMotoristaNomeNull(){
        motorista2.setNome(null);
        ModelMotorista.validar(motorista2);
    }
    
    @Test(expected = MotoristaException.class)
    public void testValidarMotoristaNomeEmpty(){
        motorista3.setNome("");
        ModelMotorista.validar(motorista3);
    }
    
    @Test(expected = MotoristaException.class)
    public void testValidarMotoristaSenhaNull(){
        motorista4.setNome("Luci");
        motorista4.setSenha(null);
        ModelMotorista.validar(motorista4);
    }
    
    @Test(expected = MotoristaException.class)
    public void testValidarMotoristaSenhaEmpty(){
        motorista5.setNome("Luci");
        motorista5.setSenha("");
        ModelMotorista.validar(motorista5);
    }
    
    @Test(expected = MotoristaException.class)
    public void testValidarMotoristaCreditoNull(){
        motorista6.setNome("Luci");
        motorista6.setSenha("123");
        motorista6.setCredito(null);
        ModelMotorista.validar(motorista6);
    }
    
    @Test(expected = MotoristaException.class)
    public void testValidarMotoristaCreditoSmallerThanZero(){
        motorista7.setNome("Luci");
        motorista7.setSenha("123");
        motorista7.setCredito(new BigDecimal("-1"));
        ModelMotorista.validar(motorista7);
    }
}