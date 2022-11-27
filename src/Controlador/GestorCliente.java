/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelos.ClienteDao;
import Modelos.Cliente;
import Modelos.PaisDao;
import Vistas.VistaCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Modelos.Pais;
import static Utils.StringUtils.capitalize;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anibal-99
 */
public class GestorCliente implements ActionListener {

    public GestorCliente() {

    }

    VistaCliente vista = new VistaCliente();
    ClienteDao cDao = new ClienteDao();
    Cliente c = new Cliente();
    DefaultTableModel modelo = new DefaultTableModel();

    public GestorCliente(VistaCliente v) throws SQLException {
        this.vista = v;
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnListar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnAgregar) {
            if (this.vista.txtCuit.getText().length() < 11) {
                JOptionPane.showMessageDialog(null, "Ingrese nuevamente el cuit");
            } else if (this.vista.txtTelefono.getText().length() < 10) {
                JOptionPane.showMessageDialog(null, "Ingrese nuevamente el telefono");
            } else {
                agregar();
                limpiarTabla();
                try {
                    listarClientes(vista.tablaCliente);
                } catch (SQLException ex) {
                    Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //limpiarCampos();
        }
        if (e.getSource() == vista.btnListar) {
            limpiarTabla();
            try {
                listarClientes(vista.tablaCliente);
            } catch (SQLException ex) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == vista.btnActualizar) {
            if (this.vista.txtCuit.getText().length() < 11) {
                JOptionPane.showMessageDialog(null, "Ingrese nuevamente el cuit");
            } else if (this.vista.txtTelefono.getText().length() < 10) {
                JOptionPane.showMessageDialog(null, "Ingrese nuevamente el telefono");
            } else {
                actualizar();
                limpiarTabla();
                try {
                    listarClientes(vista.tablaCliente);
                } catch (SQLException ex) {
                    Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        if (e.getSource() == vista.btnModificar) {
            vista.txtId.setEnabled(false);
            int fila = vista.tablaCliente.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
            } else {
                int id = Integer.parseInt((String) vista.tablaCliente.getValueAt(fila, 0).toString());
                String nombre = (String) vista.tablaCliente.getValueAt(fila, 1);
                String apellido = (String) vista.tablaCliente.getValueAt(fila, 2);
                String cuit = (String) vista.tablaCliente.getValueAt(fila, 3);
                String razonSocial = (String) vista.tablaCliente.getValueAt(fila, 4);
                String telefono = (String) vista.tablaCliente.getValueAt(fila, 5);
                Pais pais = (Pais) vista.tablaCliente.getValueAt(fila, 6);
                String direccion = (String) vista.tablaCliente.getValueAt(fila, 7);
                String localidad = (String) vista.tablaCliente.getValueAt(fila, 8);
                vista.txtId.setText("" + id);
                vista.txtNombre.setText(nombre);
                vista.txtApellido.setText(apellido);
                vista.txtCuit.setText(cuit);
                vista.txtRazonSocial.setText(razonSocial);
                vista.txtTelefono.setText(telefono);
                DefaultComboBoxModel<Pais> cbxModel = ((DefaultComboBoxModel) vista.cbxPais.getModel());
                cbxModel.setSelectedItem(pais);
                vista.txtDireccion.setText(direccion);
                vista.txtLocalidad.setText(localidad);
            }
        }
        if (e.getSource() == this.vista.btnEliminar) {
            delete();
            limpiarTabla();
            try {
                listarClientes(vista.tablaCliente);
            } catch (SQLException ex) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == this.vista.btnBuscar) {
            limpiarTabla();
            try {
                this.buscarClientes(vista.tablaCliente);
            } catch (SQLException ex) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void agregar() {
        String name = this.vista.txtNombre.getText();
        String apellido = this.vista.txtApellido.getText();
        String cuit = this.vista.txtCuit.getText();
        String razonSocial = this.vista.txtRazonSocial.getText();
        String direccion = this.vista.txtDireccion.getText();
        String localidad = this.vista.txtLocalidad.getText();
        String tel = this.vista.txtTelefono.getText();
        Pais pais = ((Pais) this.vista.cbxPais.getSelectedItem());

        c.setNombre(name);
        c.setApellido(apellido);
        c.setCuit(cuit);
        c.setRazonSocial(razonSocial);
        c.setDireccion(direccion);
        c.setLocalidad(localidad);
        c.setPais(pais);
        c.setTel(tel);

        if (this.vista.txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se puede agregar sin ingresar todos los datos");
        } else {
            try {
                cDao.agregar(c);
                JOptionPane.showMessageDialog(null, "Cliente se agrego con exito");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se agregaron los datos");
            }
        }
        this.limpiarCampos();
    }

    public void actualizar() {

        if (vista.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(vista, "No se Identifica el Id debe selecionar la opcion Editar");
        } else {
            int id = Integer.parseInt(vista.txtId.getText());
            String name = this.vista.txtNombre.getText();
            String apellido = this.vista.txtApellido.getText();
            String razonSocial = this.vista.txtRazonSocial.getText();
            String cuit = this.vista.txtCuit.getText();
            String telefono = this.vista.txtTelefono.getText();
            Pais pais = ((Pais) this.vista.cbxPais.getSelectedItem());
            String direccion = this.vista.txtDireccion.getText();
            String localidad = this.vista.txtLocalidad.getText();

            c.setId(id);
            c.setNombre(name);
            c.setApellido(apellido);
            c.setRazonSocial(razonSocial);
            c.setCuit(cuit);
            c.setTel(telefono);
            c.setPais(pais);
            c.setLocalidad(localidad);
            c.setDireccion(direccion);

            int modded = 0;
            try {
                modded = cDao.modificar(c);
            } catch (SQLException ex) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (modded == 1) {
                JOptionPane.showMessageDialog(vista, "Cliente actualizado con exito");
            } else {
                JOptionPane.showMessageDialog(vista, "Error, no se puede actualizar el cliente");
            }
        }
    }

    public void listarClientes(JTable tabla) throws SQLException {
        // Esto es para que se ejecute la tabla al momento de iniciar el programa
        modelo = (DefaultTableModel) tabla.getModel();

        List<Cliente> lista = cDao.listarClientes();

        for (Cliente c : lista) {
            Object[] object = {c.getId(), c.getNombre(), c.getApellido(), c.getCuit(), c.getRazonSocial(), c.getTel(), c.getPais(), c.getLocalidad(), c.getDireccion()};
            modelo.addRow(object);
        }
    }

    public void delete() {
        int fila = vista.tablaCliente.getSelectedRow();
        if (fila == -1) {// de esta manera el usuario solo podra eliminar si selecciona una marca sino no
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un cliente");
        } else {
            try {
                int id = Integer.parseInt((String) vista.tablaCliente.getValueAt(fila, 0).toString());
                cDao.delete(id);
                JOptionPane.showMessageDialog(vista, "Cliente eliminado");
            } catch (SQLException ex) {
                Logger.getLogger(GestorMarca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void llenarCombo() throws SQLException {
        PaisDao paises = new PaisDao();
        ArrayList<Pais> listarPaises = paises.getPais();
        DefaultComboBoxModel<Pais> cbxModel = ((DefaultComboBoxModel) vista.cbxPais.getModel());
        vista.cbxPais.removeAllItems();

        for (int i = 0; i < listarPaises.size(); i++) {
            cbxModel.addElement(listarPaises.get(i));
        }
    }

    void limpiarTabla() {
        for (int i = 0; i < vista.tablaCliente.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void buscarClientes(JTable tablaCliente) throws SQLException {
        // Esto es para que se ejecute la tabla al momento de iniciar el programa
        modelo = (DefaultTableModel) vista.tablaCliente.getModel();
        String name = capitalize(this.vista.txtBuscar.getText());
        List<Cliente> listaClientes = cDao.buscarClientes(name);

        for (Cliente c : listaClientes) {
            Object[] object = {c.getId(), c.getNombre(), c.getApellido(), c.getCuit(), c.getRazonSocial(), c.getTel(), c.getPais(), c.getLocalidad(), c.getDireccion()};
            modelo.addRow(object);
        }
    }

    public void limpiarCampos() {
        this.vista.txtId.setText("");
        this.vista.txtApellido.setText("");
        this.vista.txtNombre.setText("");
        this.vista.txtCuit.setText("");
        this.vista.txtDireccion.setText("");
        this.vista.txtLocalidad.setText("");
        this.vista.txtTelefono.setText("");
        this.vista.txtRazonSocial.setText("");
        this.vista.cbxPais.setSelectedIndex(0);
    }
}
