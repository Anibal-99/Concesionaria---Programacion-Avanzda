/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelos.ReportesDao;
import Modelos.Venta;
import Vistas.VistaListaReporteAuto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anibal-99
 */
public class GestorListaReporteAuto implements ActionListener {

    VistaListaReporteAuto vista = new VistaListaReporteAuto();
    ReportesDao reportesDao = new ReportesDao();
    DefaultTableModel modelo = new DefaultTableModel();

    public GestorListaReporteAuto() {

    }

    public GestorListaReporteAuto(VistaListaReporteAuto v) {
        this.vista = v;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void listarReporte(String valorOrden, int valorAnio) throws SQLException {
        modelo = (DefaultTableModel) this.vista.tableReporteVentaAuto.getModel();
        List<Venta> lista = reportesDao.generarReporteAuto(valorOrden, valorAnio);
        int montoTotal = 0;
        int cantidad = 0;
        for (Venta v : lista) {
            Object[] object = {
                v.getId(),
                v.getAuto(),
                v.getCantidad(),
                v.getAuto().getModelo().getMarca().getPais().getRegion().getNombre(),
                v.getMontoTotal(),
                v.getAuto().getPrecio() * v.getCantidad() - v.getAuto().getCosto() * v.getCantidad(),
                v.getVendedor(),
                v.getFecha()
            };
            modelo.addRow(object);
            montoTotal = (int) ((int) montoTotal + v.getMontoTotal());
            cantidad = cantidad + v.getCantidad();
        }
        this.vista.txtPromedioVentas.setText((montoTotal/cantidad)+"");
    }
}
