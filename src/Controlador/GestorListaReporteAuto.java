/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelos.ReportesDao;
import Modelos.modelReporteRow;
import Vistas.NumberRenderer;
import Vistas.VistaListaReporteAuto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

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
        List<modelReporteRow> lista = reportesDao.generarReporteAuto(valorOrden, valorAnio);
        this.vista.tableReporteVentaAuto.setModel(modelo);
        TableColumnModel tcm = this.vista.tableReporteVentaAuto.getColumnModel();
        tcm.getColumn(1).setCellRenderer(NumberRenderer.getCurrencyRenderer());
        tcm.getColumn(2).setCellRenderer(NumberRenderer.getCurrencyRenderer());
        tcm.getColumn(3).setCellRenderer(NumberRenderer.getCurrencyRenderer());
        float monto=0;
        int cantidad=0;

        for (modelReporteRow m : lista) {
            Object[] object = {
                m.getModelo(),
                m.getCosto(),
                m.getPrecio(),
                m.getGanancia(),
                m.getVentas()
            };
            modelo.addRow(object);
            monto = (float) m.getPrecio()+monto;
            cantidad=(int) m.getVentas()+cantidad;
        }
        this.vista.txtPromedioVentas.setText(monto/cantidad+"");
    }
}
