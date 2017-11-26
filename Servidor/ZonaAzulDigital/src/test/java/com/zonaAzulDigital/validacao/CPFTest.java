/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.validacao;

import com.zonaAzulDigital.Excecao.CpfException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author home
 */
public class CPFTest {
    
   
    public CPFTest() {
    }

    @Test
    public void deveTestarCpfToStringRetornandoStringVazia() {
        String retorno = CPF.cpfToString(null);
        
        assertEquals("", retorno);
    }
    
    //conta números com os pontos e traço
    @Test
    public void deveTestarQuantidadeCertaDeDigitosDeCpfToString(){
        String quantidade = CPF.cpfToString(60071222472L);
        int tamanho = quantidade.length();
        assertEquals(14,tamanho);
  
    }
    
    // SUGESTÃO: excecão TAMANHO não usada no código fonte
    @Test
    public void deveTestarValidarCPFPassandoCPFMenorLancandoCpfExceptionTAMANHO(){
         try {
            CPF.validarCPF("6007122247");
            fail();
        } catch (CpfException ex) {
            assertEquals(String.format(CpfException.TAMANHO), ex.getMessage());
        }
  
    }
    
     
    @Test
    public void deveTestarValidarCPFPassandoCPFMenorLancandoCpfException(){
         try {
            CPF.validarCPF("6007122247");
            fail();
        } catch (CpfException ex) {
            assertEquals(String.format(CpfException.NAOVALIDO), ex.getMessage());
        }
  
    }
    // SUGESTÃO: excecão TAMANHO não usada no código fonte
    @Test
    public void deveTestarValidarCPFPassandoCPFMaiorLancandoCpfExceptionTAMANHO(){
         try {
            CPF.validarCPF("600712224722");
            fail();
        } catch (CpfException ex) {
            assertEquals(String.format(CpfException.TAMANHO), ex.getMessage());
        }
  
    }
    //sem comparação do tamanho no código
    @Test
    public void deveTestarValidarCPFPassandoCPFMaiorLancandoCpfException(){
         try {
            CPF.validarCPF("600712224722");
            fail();
        } catch (CpfException ex) {                                                     
            assertEquals(String.format(CpfException.NAOVALIDO), ex.getMessage());
        }
  
    }
    
    @Test
    public void deveTestarCpfToStringRetornandoCpfComoString(){        
        String retorno = CPF.cpfToString(70564377457L);
        
        assertEquals("705.643.774-57", retorno);
    }
    
    @Test
    public void deveTestarCpfToStringIniciandoComZero(){
        //Este está passando
        
        String retorno = CPF.cpfToString(12546452401L);
        
        assertEquals("125.464.524-01", retorno);
        
        //Está falhando quando o CPF começa com 0
        
        String retornoCPFZero = CPF.cpfToString(02546452401L);
        
        assertEquals("025.464.524-01", retornoCPFZero);
    }

    @Test
    public void deveTestarValidarCPFPassandoCPFNuloLancandoCpfException() {
        try {
            CPF.validarCPF(null);
            fail();
        } catch (CpfException ex) {
            assertEquals(String.format(CpfException.NULL), ex.getMessage());
        }
    }
    
    
    @Test
    public void deveTestarValidarCPFPassandoCPFVazioLancandoCpfException() {
        try {
            CPF.validarCPF("");
            fail();
        } catch (CpfException ex) {
            assertEquals(String.format(CpfException.NULL), ex.getMessage());
        }
    }
    
    @Test
    public void deveTestarValidarCPFPassandoCPFsComSequenciaIgual0(){
        try {
            CPF.validarCPF("00000000000");
            fail();
        } catch (CpfException ex) {
            assertEquals(String.format(CpfException.NAOVALIDO), ex.getMessage());
        }
    }
    
    @Test
    public void deveTestarValidarCPFPassandoCPFsComSequenciaIgual1(){
        try {
            CPF.validarCPF("11111111111");
            fail();
        } catch (CpfException ex) {
            assertEquals(String.format(CpfException.NAOVALIDO), ex.getMessage());
        }
    }
    
    @Test
    public void deveTestarValidarCPFPassandoCPFsComSequenciaIgual2(){
        try {
            CPF.validarCPF("22222222222");
            fail();
        } catch (CpfException ex) {
            assertEquals(String.format(CpfException.NAOVALIDO), ex.getMessage());
        }
    }
    
    @Test
    public void deveTestarValidarCPFPassandoCPFsComSequenciaIgual3(){
        try {
            CPF.validarCPF("33333333333");
            fail();
        } catch (CpfException ex) {
            assertEquals(String.format(CpfException.NAOVALIDO), ex.getMessage());
        }
    }
    
    @Test
    public void deveTestarValidarCPFPassandoCPFsComSequenciaIgual4(){
        try {
            CPF.validarCPF("44444444444");
            fail();
        } catch (CpfException ex) {
            assertEquals(String.format(CpfException.NAOVALIDO), ex.getMessage());
        }
    }
    
    @Test
    public void deveTestarValidarCPFPassandoCPFsComSequenciaIgual5(){
        try {
            CPF.validarCPF("55555555555");
            fail();
        } catch (CpfException ex) {
            assertEquals(String.format(CpfException.NAOVALIDO), ex.getMessage());
        }
    }
    
    @Test
    public void deveTestarValidarCPFPassandoCPFsComSequenciaIgual6(){
        try {
            CPF.validarCPF("66666666666");
            fail();
        } catch (CpfException ex) {
            assertEquals(String.format(CpfException.NAOVALIDO), ex.getMessage());
        }
    }
    
    @Test
    public void deveTestarValidarCPFPassandoCPFsComSequenciaIgual7(){
        try {
            CPF.validarCPF("77777777777");
            fail();
        } catch (CpfException ex) {
            assertEquals(String.format(CpfException.NAOVALIDO), ex.getMessage());
        }
    }
    
    @Test
    public void deveTestarValidarCPFPassandoCPFsComSequenciaIgual8(){
        try {
            CPF.validarCPF("88888888888");
            fail();
        } catch (CpfException ex) {
            assertEquals(String.format(CpfException.NAOVALIDO), ex.getMessage());
        }
    }
    
    @Test
    public void deveTestarValidarCPFPassandoCPFsComSequenciaIgual9(){
        try {
            CPF.validarCPF("99999999999");
            fail();
        } catch (CpfException ex) {
            assertEquals(String.format(CpfException.NAOVALIDO), ex.getMessage());
        }
    }
    @Test(expected = Exception.class)
    public void deveTestarCpfToStringPassandoLongMenor(){
        CPF.cpfToString(6007122247L);
    }
    
    @Test(expected = Exception.class)
    public void deveTestarCpfToStringPassandoInteiro(){
        CPF.cpfToString(1234567L);
    }
    
    @Test(expected = Exception.class)
    public void deveTestarCpfToStringPassandoLongMaior(){
        CPF.cpfToString(600712224721L);
    }

    @Test
    public void deveTestarValidarCPFPassandoCPFValidoRetornandoTrue() throws CpfException {
        boolean retorno = CPF.validarCPF("70564377457");
        assertTrue(retorno);
    }
    
    @Test
    public void deveTestarValidarCPFPassandoCPFComPrimeiroDigitoInvalidoLancandoCpfException() {
        try {
            CPF.validarCPF("70564377417");
            fail();
        } catch (CpfException ex) {
            assertEquals(String.format(CpfException.NAOVALIDO), ex.getMessage());
        }
    }
    
    @Test
    public void deveTestarValidarCPFPassandoCPFComSegundoDigitoInvalidoLancandoCpfException(){
        try {
            CPF.validarCPF("70564377458");
            fail();
        } catch (CpfException ex) {
            assertEquals(String.format(CpfException.NAOVALIDO), ex.getMessage());
        }
    }
    
    @Test
    public void deveTestarValidarCPFPAssandoCPFComTamanhoMenorLancandoCpfException(){
        try {
            CPF.validarCPF("7056437745");
            fail();
        } catch (CpfException ex) {
            assertEquals(String.format(CpfException.NAOVALIDO), ex.getMessage());
        }
    }
    
    @Test
    public void deveTestarValidarCPFPassandoCPFComTamanhoMaiorLancandoCpfException(){
        
        try {
            CPF.validarCPF("705643774571");
            fail();
        } catch (CpfException ex) {
            assertEquals(String.format(CpfException.NAOVALIDO), ex.getMessage());
        }
    }
    
    @Test
    public void deveTestarValidarCPFPassandoCPFComCaracteresNoInicioLancandoCPFException(){
        try {
            CPF.validarCPF("abc123*7457");
            fail();
        } catch (CpfException ex) {
            assertEquals(String.format(CpfException.NAOVALIDO), ex.getMessage());
        }
    }
    
    @Test
    public void deveTestarValidarCPFPassandoCPFComCaracteresNoFimLancandoCpfException(){
        try {
            CPF.validarCPF("705643774*abc");
            fail();
        } catch (CpfException ex) {
            assertEquals(String.format(CpfException.NAOVALIDO), ex.getMessage());
        }
    }
    
    @Test
    public void deveTestarValidarCPFPassandoCPFComCaracteresNoMeioLancandoCpfException(){
        try {
            CPF.validarCPF("705abc$77457");
            fail();
        } catch (CpfException ex) {
            assertEquals(String.format(CpfException.NAOVALIDO), ex.getMessage());
        }
    }
}
