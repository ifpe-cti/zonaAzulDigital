/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.model;

import com.zonaAzulDigital.DAO.DaoCompraCartaoZADB;
import com.zonaAzulDigital.entidades.CartaoZonaAzul;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.interfaces.DAOCartaoZonaAzul;
import com.zonaAzulDigital.interfaces.DAOCompraCartaoZA;
import com.zonaAzulDigital.interfaces.DAOMotorista;
import com.zonaAzulDigital.interfaces.ModelCartaoZonaAzulInterface;
import com.zonaAzulDigital.tests.DAO.DAOCartaoZonaAzulFake;
import com.zonaAzulDigital.tests.DAO.DAOCompraCartaoZAFake;
import com.zonaAzulDigital.tests.DAO.DAOMotoristaFake;
import com.zonaAzulDigital.tests.DAO.DAOPlacaFake;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author JonasJr
 */
public class ModelCartaoZonaAzulTest {
    
    private ModelCartaoZonaAzulInterface modelCarttaoZA;
    private DAOMotorista dAOMotorista;
    private DAOCartaoZonaAzul dAOCartaoZonaAzul;
    private DAOCompraCartaoZA dAOCompraCartaoZA;
    
    public ModelCartaoZonaAzulTest() {
        
    }
    /*
    @BeforeClass
    public void configAmbienteTeste() {
        this.modelCarttaoZA = new ModelCartaoZonaAzul(new DAOMotoristaFake(), new DAOCartaoZonaAzulFake(),
                new DAOCompraCartaoZAFake(), new DAOPlacaFake());
    }*/
  
    @Ignore
    @Test
    public void deveLancarExcecaoSeMotoristaNaoCadastrado() {
        
        
    }
    @Ignore
    @Test
    public void deveLancarExcecaoSeMotoristaNaoTiverCreditoSuficiente() {
        
        
    }
    @Ignore
    @Test
    public void deveRegistrarUmaCompraDeCartao(){
        
    }

    
    
}
