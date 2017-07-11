/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zonaAzulDigital.controler;

import com.zonaAzulDigital.DAO.DaoCartaoZonaAzulBD;
import com.zonaAzulDigital.DAO.DaoCompraCartaoZADB;
import com.zonaAzulDigital.DAO.DaoMotoristaBD;
import com.zonaAzulDigital.DAO.DaoPlacaBD;
import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.constante.Meses;
import com.zonaAzulDigital.entidades.CompraCartaoZA;
import com.zonaAzulDigital.entidades.VendaMes;
import com.zonaAzulDigital.interfaces.DAOCartaoZonaAzul;
import com.zonaAzulDigital.interfaces.DAOCompraCartaoZA;
import com.zonaAzulDigital.interfaces.DAOMotorista;
import com.zonaAzulDigital.interfaces.DAOPlaca;
import com.zonaAzulDigital.interfaces.ModelCartaoZonaAzulInterface;
import com.zonaAzulDigital.model.ModelCartaoZonaAzul;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

/**
 *
 * @author JonasJr
 */
@ManagedBean
@ViewScoped
public class controllerRelatorio implements Serializable {

    private LineChartModel graficoLinha;
    private DAOPlaca dAOPlaca;
    private DAOMotorista dAOMotorista;
    private DAOCartaoZonaAzul dAOCartaoZonaAzul;
    private DAOCompraCartaoZA dAOCompraCartaoZA;
    private ModelCartaoZonaAzulInterface modelCartaoZonaAzul;

    public LineChartModel getGraficoLinha() {
        return graficoLinha;
    }
    @PostConstruct
    public void init() {
        dAOPlaca = new DaoPlacaBD();
        dAOMotorista = new DaoMotoristaBD();
        dAOCartaoZonaAzul = new DaoCartaoZonaAzulBD();
        dAOCompraCartaoZA = new DaoCompraCartaoZADB();
        modelCartaoZonaAzul = new ModelCartaoZonaAzul(dAOMotorista, dAOCartaoZonaAzul, dAOCompraCartaoZA, dAOPlaca);
        graficoLinha = new LineChartModel();
        ChartSeries cartoes = new ChartSeries();
        cartoes.setLabel("Cartões");
        
        List<VendaMes> comprasNoMes = new ArrayList<>();
        try {
            comprasNoMes = modelCartaoZonaAzul.vendasNoMes(2017);
        } catch (DaoException ex) {
            Logger.getLogger(controllerRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        long escala = 0;
        for (int i = 0; i < comprasNoMes.size(); i++) {
            String s = Meses.values()[comprasNoMes.get(i).getMes()].name();
            cartoes.set(s, comprasNoMes.get(i).getQuantidade());
            if(comprasNoMes.get(i).getQuantidade().compareTo(escala) > 0){
                escala = comprasNoMes.get(i).getQuantidade();
            }
        }
         
        
        graficoLinha.addSeries(cartoes);
        graficoLinha.setTitle("Vendas no mês");
        graficoLinha.setLegendPosition("e");
        Axis yAxis = graficoLinha.getAxis(AxisType.Y);
        Axis xAxis = graficoLinha.getAxis(AxisType.X);
        
        graficoLinha.setShowPointLabels(true);
        graficoLinha.getAxes().put(AxisType.X, new CategoryAxis("Mes"));
        yAxis.setLabel("Quantidade");
        yAxis.setMin(0);
        yAxis.setMax(escala + 3);
    }
    
    
   

}
