/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelos.Venta;
import Modelos.ReportesDao;
import Vistas.NumberRenderer;
import Vistas.VistaListaReporteAuto;
import Vistas.VistaReporte;
import Vistas.VistaReporteAuto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Anibal-99
 */
public class GestorReporteAuto implements ActionListener {

    VistaReporteAuto vista = new VistaReporteAuto();
    ReportesDao reportesDao = new ReportesDao();
    DefaultTableModel modelo = new DefaultTableModel();

    public GestorReporteAuto() {

    }

    public GestorReporteAuto(VistaReporteAuto v) throws SQLException {
        this.vista = v;
        this.vista.btnGenerar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista.btnGenerar) {
            try {
                generarReporteAuto();
            } catch (SQLException ex) {
                Logger.getLogger(GestorReporteAuto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void generarReporteAuto() throws SQLException {
        String order = (String) this.vista.cbxOrdenacion.getSelectedItem();
        int valorAnio = (int) this.vista.spinAnio.getValue();

        if (order == "Cantidad de ventas") {
            order = "ventas";
        }else{
            order="ganancia";
        }

        VistaListaReporteAuto vistaReporteAuto = new VistaListaReporteAuto();
        GestorListaReporteAuto gestorReporte = new GestorListaReporteAuto(vistaReporteAuto);
        gestorReporte.listarReporte(order, valorAnio);
        vistaReporteAuto.setVisible(true);
    }

}
