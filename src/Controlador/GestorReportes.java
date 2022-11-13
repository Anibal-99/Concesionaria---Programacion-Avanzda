/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelos.Conexion;
import Modelos.ReportesDao;
import Vistas.VistaReportes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Anibal-99
 */
public class GestorReportes implements ActionListener {

    VistaReportes vReportes = new VistaReportes();

    public GestorReportes(VistaReportes vReportes) {
        this.vReportes = vReportes;
        this.vReportes.btnGenerar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vReportes.btnGenerar) {
            try {
                generarReporteVendedor();
            } catch (SQLException ex) {
                Logger.getLogger(GestorReportes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void generarReporteVendedor() throws SQLException {
        try {
            Connection con;
            Conexion conectar = new Conexion();
            con = conectar.getConection();

            JasperReport reporte = null;
            String path = "src\\Reportes\\reporteVendedores.jrxml";

            JasperReport jr = JasperCompileManager.compileReport(path);
            //LO IMPRIME
            JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
            //LO MUESTRA
            JasperViewer.viewReport(jp, false);
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(GestorReportes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(GestorReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
