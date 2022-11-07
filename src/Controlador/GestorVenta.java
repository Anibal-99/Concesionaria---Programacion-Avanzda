/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelos.Auto;
import Modelos.AutoDAO;
import Modelos.Cliente;
import Modelos.ClienteDao;
import Modelos.Region;
import Modelos.RegionDao;
import Modelos.Vendedor;
import Modelos.VendedorDao;
import Modelos.Venta;
import Modelos.VentaDao;
import Vistas.NumberRenderer;

import Vistas.VistaVenta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Anibal-99
 */
public class GestorVenta implements ActionListener {

    VistaVenta vistaVenta = new VistaVenta();
    Venta v = new Venta();
    VentaDao vDao = new VentaDao();
    DefaultTableModel modelo = new DefaultTableModel();

    public GestorVenta(VistaVenta vistaVenta) {
        this.vistaVenta = vistaVenta;
        this.vistaVenta.btnOkCliente.addActionListener(this);
        this.vistaVenta.btnOkCliente.addActionListener(this);
        this.vistaVenta.btnAgregar.addActionListener(this);
        this.vistaVenta.btnEliminar.addActionListener(this);
        this.vistaVenta.btnNuevo.addActionListener(this);
        this.vistaVenta.btnOkCliente.addActionListener(this);
        this.vistaVenta.btnOkAuto.addActionListener(this);
        this.vistaVenta.btnCalcular.addActionListener(this);
        this.vistaVenta.btnListarVenta.addActionListener(this);
        this.vistaVenta.btnBuscarVenta.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaVenta.btnOkCliente) {
            this.obtenerCliente();
        }
        if (e.getSource() == vistaVenta.btnOkAuto) {
            this.obtenerAuto();
        }
        if (e.getSource() == vistaVenta.btnCalcular) {
            float importe = 0;
            try {
                importe = this.calcularImpuesto();
            } catch (SQLException ex) {
                Logger.getLogger(GestorVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
            int cantidad = (int) this.vistaVenta.cantAutos.getValue();
            float monto = Float.parseFloat(this.vistaVenta.txtPrecio.getText());
            float montoTotal = (monto * cantidad) + importe;
            vistaVenta.txtTotal.setText(montoTotal + "");
        }
        if (e.getSource() == this.vistaVenta.btnAgregar) {
            this.agregar();
            limpiarTabla();
            nuevo();
        }
        if (e.getSource() == this.vistaVenta.btnListarVenta) {
            limpiarTabla();
            try {
                listarVentas(vistaVenta.tableVenta);
            } catch (SQLException ex) {
                Logger.getLogger(GestorVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
            nuevo();
        }
        if (e.getSource() == this.vistaVenta.btnEliminar) {
            this.delete();
            limpiarTabla();
            try {
                listarVentas(vistaVenta.tableVenta);
            } catch (SQLException ex) {
                Logger.getLogger(GestorVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
            nuevo();
        }
        if (e.getSource() == this.vistaVenta.btnNuevo) {
            nuevo();
        }
    }

    public void llenarComboCliente() throws SQLException {
        ClienteDao clientesDao = new ClienteDao();
        ArrayList<Cliente> clientes = clientesDao.getClientes();
        DefaultComboBoxModel<Cliente> cbxCliente = ((DefaultComboBoxModel) vistaVenta.cbxCliente.getModel());
        vistaVenta.cbxCliente.removeAllItems();

        for (int i = 0; i < clientes.size(); i++) {
            cbxCliente.addElement(clientes.get(i));
        }
    }

    public void llenarComboAuto() throws SQLException {
        AutoDAO autoDao = new AutoDAO();
        ArrayList<Auto> autos = autoDao.getAutos();
        DefaultComboBoxModel<Auto> cbxAuto = ((DefaultComboBoxModel) vistaVenta.cbxAuto.getModel());
        vistaVenta.cbxAuto.removeAllItems();

        for (int i = 0; i < autos.size(); i++) {
            cbxAuto.addElement(autos.get(i));
        }
    }

    public void llenarComboVendedor() throws SQLException {
        VendedorDao vendedorDao = new VendedorDao();
        ArrayList<Vendedor> vendedor = vendedorDao.getVendedores();
        DefaultComboBoxModel<Vendedor> cbxVend = ((DefaultComboBoxModel) vistaVenta.cbxVendedor.getModel());
        vistaVenta.cbxVendedor.removeAllItems();

        for (int i = 0; i < vendedor.size(); i++) {
            cbxVend.addElement(vendedor.get(i));
        }
    }

    public void obtenerCliente() {
        Cliente cliente = ((Cliente) this.vistaVenta.cbxCliente.getSelectedItem());
        vistaVenta.txtCuit.setText(cliente.getCuit());
        vistaVenta.txtTelefono.setText(cliente.getTel());
        vistaVenta.txtPais.setText(cliente.getPais().getName());
        vistaVenta.txtLocalidad.setText(cliente.getLocalidad());
        vistaVenta.txtDireccion.setText(cliente.getDireccion());
    }

    public void obtenerAuto() {
        Auto auto = ((Auto) this.vistaVenta.cbxAuto.getSelectedItem());
        vistaVenta.txtColor.setText(auto.getColor().getNombre());
        vistaVenta.txtPrecio.setText(auto.getPrecio() + "");
    }

    public float calcularImpuesto() throws SQLException {
        vistaVenta.txtImpuesto.setText("");
        Auto auto = ((Auto) this.vistaVenta.cbxAuto.getSelectedItem());
        int cantidad = (int) this.vistaVenta.cantAutos.getValue();
        float monto = Float.parseFloat(this.vistaVenta.txtPrecio.getText());

        vistaVenta.txtMonto.setText(monto * cantidad + "");

        RegionDao regionDao = new RegionDao();
        ArrayList<Region> regiones = regionDao.getRegion();

        float importe = 0;
        for (Region r : regiones) {
            if (auto.getModelo().getMarca().getPais().getRegion().getNombre().equals("Extrangero")) {
                importe = (float) ((monto * cantidad) * 0.2);
                vistaVenta.txtImpuesto.setText(importe + "");
                break;
            } else if (auto.getModelo().getMarca().getPais().getRegion().getNombre().equals("Sudamerica")) {
                importe = (float) ((monto * cantidad) * 0.1);
                vistaVenta.txtImpuesto.setText(importe + "");
                break;
            }
        }
        return importe;
    }

    public void agregar() {
        Date fecha = new Date();
        SimpleDateFormat fechaAct = new SimpleDateFormat("dd/MM/YYYY");

        int cantidad = (int) this.vistaVenta.cantAutos.getValue();
        float montoTotal = Float.parseFloat(this.vistaVenta.txtTotal.getText());
        float impuesto = Float.parseFloat(this.vistaVenta.txtImpuesto.getText());
        Cliente cliente = ((Cliente) this.vistaVenta.cbxCliente.getSelectedItem());
        Vendedor vendedor = ((Vendedor) this.vistaVenta.cbxVendedor.getSelectedItem());
        Auto auto = (Auto) this.vistaVenta.cbxAuto.getSelectedItem();

        v.setAuto(auto);
        v.setCliente(cliente);
        v.setVendedor(vendedor);
        v.setFecha(fechaAct.format(fecha));
        v.setCantidad(cantidad);
        v.setMontoTotal(montoTotal);
        v.setImpuesto(impuesto);

        if (this.vistaVenta.txtTotal.getText().isEmpty()) {
            System.out.println("LLLEGOOOOO");
            JOptionPane.showMessageDialog(null, "No se puede agregar sin ingresar todos los datos");
        } else {
            try {
                vDao.agregar(v);
                JOptionPane.showMessageDialog(null, "Venta se agrego con exito");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se agregaron los datos");
            }
        }
    }

    public void listarVentas(JTable tableVenta) throws SQLException {
        // Esto es para que se ejecute la tabla al momento de iniciar el programa
        modelo = (DefaultTableModel) tableVenta.getModel();

        List<Venta> lista = vDao.listarVentas();

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
        TableColumnModel tcm = vistaVenta.tableVenta.getColumnModel();
        tcm.getColumn(4).setCellRenderer(NumberRenderer.getCurrencyRenderer());
        tcm.getColumn(5).setCellRenderer(NumberRenderer.getCurrencyRenderer());
        tcm.getColumn(6).setCellRenderer(NumberRenderer.getCurrencyRenderer());
        
    }

    public void delete() {
        int fila = vistaVenta.tableVenta.getSelectedRow();
        if (fila == -1) {// de esta manera el usuario solo podra eliminar si selecciona una marca sino no
            JOptionPane.showMessageDialog(vistaVenta, "Debe seleccionar una venta");
        } else {
            try {
                int id = Integer.parseInt((String) vistaVenta.tableVenta.getValueAt(fila, 0).toString());
                vDao.delete(id);
                JOptionPane.showMessageDialog(vistaVenta, "Venta eliminada");
            } catch (SQLException ex) {
                Logger.getLogger(GestorMarca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    void limpiarTabla() {
        for (int i = 0; i < vistaVenta.tableVenta.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    void nuevo() {
        vistaVenta.txtNroVenta.setText("");
        vistaVenta.txtFecha.setText("");
        vistaVenta.txtPais.setText("");
        vistaVenta.txtCuit.setText("");
        vistaVenta.txtTelefono.setText("");
        vistaVenta.txtLocalidad.setText("");
        vistaVenta.txtDireccion.setText("");
        vistaVenta.txtPrecio.setText("");
        vistaVenta.txtColor.setText("");
        vistaVenta.txtMonto.setText("");
        vistaVenta.txtImpuesto.setText("");
        vistaVenta.txtTotal.setText("");
    }
}
