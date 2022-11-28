/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelos.GraficoDao;
import Vistas.VistaGrafico;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Anibal-99
 */
public class GestorGrafico {

    public GestorGrafico() {

    }
    VistaGrafico vista = new VistaGrafico();
    
    public GestorGrafico(VistaGrafico v) throws SQLException{
        this.vista=v;
    }
    
    public void generarGrafico() throws SQLException {
        int ventasLocal=0;
        int ventasSudamerica=0;
        int ventasExtranjera=0;
        VistaGrafico vista = new VistaGrafico();
        GraficoDao grafico = new GraficoDao();
        try {
            ventasLocal = grafico.obtenerVentasRegionLocal();
            ventasSudamerica = grafico.obtenerVentasRegionSudamerica();
            ventasExtranjera = grafico.obtenerVentasRegionExtranjero();
            System.out.println(ventasSudamerica);
            System.out.println(ventasLocal);
            System.out.println(ventasExtranjera);
        }catch(Exception e){
            
        }
        
        DefaultPieDataset datos = new DefaultPieDataset();
        datos.setValue("Region local", ventasLocal);
        datos.setValue("Region sudamericana", ventasSudamerica);
        datos.setValue("Region extranjera", ventasExtranjera);
        
        JFreeChart graficoTorta= ChartFactory.createPieChart(
        "Ventas por cada region", datos, true, true, false
        );
        
        ChartPanel panel = new ChartPanel(graficoTorta);
        panel.setMouseWheelEnabled(true);
        //Dimension dimen= new Dimension();
        panel.setPreferredSize(new Dimension(500, 400));
        
        this.vista.jPanel.setLayout(new BorderLayout());
        this.vista.jPanel.add(panel, BorderLayout.NORTH);
        
        this.vista.pack(); // esto es para asegurar de que el grafico no se borre
        this.vista.repaint();
        //vista.setVisible(true);
    }
}
