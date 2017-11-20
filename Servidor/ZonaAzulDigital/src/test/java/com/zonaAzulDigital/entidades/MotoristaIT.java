/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.entidades;

import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 20141D12GR0114
 */
public class MotoristaIT {

    public MotoristaIT() {
    }

    @Test
    public void testDebitarPositivo() {
        Motorista motorista = new Motorista();
        motorista.setCredito(BigDecimal.valueOf(11.0));
        boolean result = motorista.debitar(BigDecimal.valueOf(10.0));
        assertTrue(result);

    }

    @Test
    public void testDebitarNegativo() {
        Motorista motorista = new Motorista();
        motorista.setCredito(BigDecimal.valueOf(9.0));
        boolean result = motorista.debitar(BigDecimal.valueOf(10.0));
        assertFalse(result);

    }

    @Test
    public void testCreditarAumetando() {
        Motorista motorista = new Motorista();
        motorista.setCredito(BigDecimal.valueOf(10.0));
        boolean result = motorista.creditar(BigDecimal.valueOf(5.0));
        assertTrue(result);
    }

    @Test
    public void testCreditarSemAumento() {
        Motorista motorista = new Motorista();
        motorista.setCredito(BigDecimal.valueOf(10.0));
        boolean result = motorista.creditar(BigDecimal.valueOf(0.0));
        assertFalse(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreditarValorNegativo() {
        Motorista motorista = new Motorista();
        motorista.setCredito(BigDecimal.valueOf(0.0));
        boolean result = motorista.creditar(BigDecimal.valueOf(-5.0));
    }

}
