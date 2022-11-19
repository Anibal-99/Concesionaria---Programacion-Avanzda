/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelos.ReportesDao;
import Modelos.modelReporteRow;
import Modelos.vendedorReporteRow;
import Vistas.VistaListaReporteVendedores;
import Vistas.VistaReporteAuto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anibal-99
 */
public class GestorListaReporteVendedores implements ActionListener {

    VistaListaReporteVendedores vista = new VistaListaReporteVendedores();
    ReportesDao reportesDao = new ReportesDao();
    DefaultTableModel modelo = new DefaultTableModel();

    public GestorListaReporteVendedores() {

    }

    public GestorListaReporteVendedores(VistaListaReporteVendedores vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void listarReporte(String valorOrder, int valorAnio) throws SQLException {
        modelo = (DefaultTableModel) this.vista.tableVendedores.getModel();
        List<vendedorReporteRow> lista = reportesDao.generarReporteVendedores(valorOrder, valorAnio);
        float ganancia=0;
        int cantidad= lista.size();

        for (vendedorReporteRow v : lista) {
            Object[] object = {
                v.getVendedor(),
                v.getGanancia(),
                v.getVentas()
            };
            modelo.addRow(object);
            ganancia= v.getGanancia() + ganancia;
        }
        this.vista.txtMontoPromedio.setText(ganancia/cantidad +"");
    }
}
