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
import Vistas.VistaVenta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Anibal-99
 */
public class GestorVenta implements ActionListener {

    VistaVenta vistaVenta = new VistaVenta();

    public GestorVenta(VistaVenta vistaVenta) {
        this.vistaVenta = vistaVenta;
        this.vistaVenta.btnOkCliente.addActionListener(this);
        this.vistaVenta.btnOkCliente.addActionListener(this);
        this.vistaVenta.btnAgregar.addActionListener(this);
        this.vistaVenta.btnModificar.addActionListener(this);
        this.vistaVenta.btnActualizar.addActionListener(this);
        this.vistaVenta.btnEliminar.addActionListener(this);
        this.vistaVenta.btnNuevo.addActionListener(this);
        this.vistaVenta.btnOkCliente.addActionListener(this);
        this.vistaVenta.btnOkAuto.addActionListener(this);
        this.vistaVenta.btnCalcular.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaVenta.btnOkCliente) {
            this.obtenerCliente();
        }
        if (e.getSource() == vistaVenta.btnOkAuto) {
            this.obtenerAuto();
        }
        if(e.getSource()==vistaVenta.btnCalcular){
            try {
                this.calcularImpuesto();
            } catch (SQLException ex) {
                Logger.getLogger(GestorVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    public void calcularImpuesto() throws SQLException {
        vistaVenta.txtImpuesto.setText("");
        Auto auto = ((Auto) this.vistaVenta.cbxAuto.getSelectedItem());
        int cantidad = (int) this.vistaVenta.cantAutos.getValue();
        float monto = Float.parseFloat(this.vistaVenta.txtPrecio.getText());
        
        vistaVenta.txtMonto.setText(monto*cantidad+"");

        RegionDao regionDao = new RegionDao();
        ArrayList<Region> regiones = regionDao.getRegion();

        float importe;
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
    }
}
