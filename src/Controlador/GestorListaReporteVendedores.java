/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelos.ReportesDao;
import Vistas.VistaListaReporteVendedores;
import Vistas.VistaReporteAuto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anibal-99
 */
public class GestorListaReporteVendedores implements ActionListener {

    VistaListaReporteVendedores vista = new VistaListaReporteVendedores();
    ReportesDao reportesDao = new ReportesDao();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public GestorListaReporteVendedores(){
        
    }
    
    public GestorListaReporteVendedores(VistaListaReporteVendedores vista){
        this.vista=vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    
    public void listarReporte(String valorOrder, int valorAnio){
        
    }
}
