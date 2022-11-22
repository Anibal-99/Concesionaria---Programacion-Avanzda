/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vistas.VistaListaReporteAuto;
import Vistas.VistaListaReporteVendedores;
import Vistas.VistaReporteAuto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anibal-99
 */
public class GestorReporteVendedores implements ActionListener {

    VistaReporteAuto vista = new VistaReporteAuto();

    public GestorReporteVendedores() {

    }

    public GestorReporteVendedores(VistaReporteAuto vista) {
        this.vista = vista;
        this.vista.btnGenerar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista.btnGenerar) {
            try {
                generarReporte();
            } catch (SQLException ex) {
                Logger.getLogger(GestorReporteVendedores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void generarReporte() throws SQLException {
        String order = (String) this.vista.cbxOrdenacion.getSelectedItem();
        int valorAnio = (int) this.vista.spinAnio.getValue();

        if (order == "Cantidad de ventas") {
            order = "ventas";
        } else {
            order = "ganancia";
        }

        VistaListaReporteVendedores vistaReporteVendedor = new VistaListaReporteVendedores();
        GestorListaReporteVendedores gestorReporte = new GestorListaReporteVendedores(vistaReporteVendedor);
        gestorReporte.listarReporte(order, valorAnio);
        vistaReporteVendedor.setVisible(true);
    }

}
