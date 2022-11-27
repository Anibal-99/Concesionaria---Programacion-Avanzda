/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelos.Venta;
import Modelos.VentaDao;
import Vistas.NumberRenderer;
import Vistas.VistaListaVentas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Anibal-99
 */
public class GestorListaVenta implements ActionListener {

    VistaListaVentas vistaVenta = new VistaListaVentas();
    DefaultTableModel modelo = new DefaultTableModel();
    VentaDao vDao = new VentaDao();

    public GestorListaVenta(VistaListaVentas vistaVenta) {
        this.vistaVenta = vistaVenta;
        this.vistaVenta.btnLimpiarFiltros.addActionListener(this);
        this.vistaVenta.btnEliminar.addActionListener(this);
        this.vistaVenta.btnBuscar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vistaVenta.btnLimpiarFiltros) {
            limpiarTabla();
            limpiarFiltros();
            try {buscar(vistaVenta.tableVentas);}catch(SQLException exe){};
        }
        if (e.getSource() == this.vistaVenta.btnEliminar) {
            this.delete();
            limpiarTabla();
            try {
                buscar(vistaVenta.tableVentas);
            } catch (SQLException ex) {
                Logger.getLogger(GestorVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == this.vistaVenta.btnBuscar) {
            this.vistaVenta.btnLimpiarFiltros.setEnabled(true);
            limpiarTabla();
            try {
                buscar(vistaVenta.tableVentas);
            } catch (SQLException ex) {
                Logger.getLogger(GestorListaVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void limpiarFiltros() {
        this.vistaVenta.txtFechaDesde.setText("");
        this.vistaVenta.txtFechaHasta.setText("");
        this.vistaVenta.txtMontoDesde.setText("");
        this.vistaVenta.txtMontoHasta.setText("");
    }

    void limpiarTabla() {
        for (int i = 0; i < vistaVenta.tableVentas.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void delete() {
        int fila = vistaVenta.tableVentas.getSelectedRow();
        if (fila == -1) {// de esta manera el usuario solo podra eliminar si selecciona una marca sino no
            JOptionPane.showMessageDialog(vistaVenta, "Debe seleccionar una venta");
        } else {
            try {
                int id = Integer.parseInt((String) vistaVenta.tableVentas.getValueAt(fila, 0).toString());
                vDao.delete(id);
                JOptionPane.showMessageDialog(vistaVenta, "Venta eliminada");
            } catch (SQLException ex) {
                Logger.getLogger(GestorMarca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void buscar(JTable tablaVentas) throws SQLException {
        modelo = (DefaultTableModel) tablaVentas.getModel();
        String fechaDesde = this.vistaVenta.txtFechaDesde.getText();
        String fechaHasta = this.vistaVenta.txtFechaHasta.getText();
        Float montoDesde;
        Float montoHasta;
        try {
            montoDesde = Float.parseFloat(this.vistaVenta.txtMontoDesde.getText());
            montoHasta = Float.parseFloat(this.vistaVenta.txtMontoHasta.getText());
        } catch(Exception e) {
            montoDesde = Float.NaN;
            montoHasta = Float.NaN;
        }

        List<Venta> lista = vDao.buscarVentas(fechaDesde, fechaHasta, montoDesde, montoHasta);

        for (Venta v : lista) {
            Object[] object = {
                v.getId(),
                v.getCliente(),
                v.getAuto(),
                v.getCantidad(),
                v.getAuto().getPrecio(),
                v.getImpuesto(),
                v.getMontoTotal(),
                v.getFecha(),
                v.getVendedor()
            };
            modelo.addRow(object);
        }
    }

}
