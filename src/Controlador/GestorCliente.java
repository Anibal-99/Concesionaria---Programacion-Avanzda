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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            agregar();
            limpiarTabla();
            try {
                listarClientes(vista.tablaCliente);
            } catch (SQLException ex) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            actualizar();
            limpiarTabla();
            try {
                listarClientes(vista.tablaCliente);
            } catch (SQLException ex) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
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
                String pais = (String) vista.tablaCliente.getValueAt(fila, 6);
                String direccion = (String) vista.tablaCliente.getValueAt(fila, 7);
                String localidad = (String) vista.tablaCliente.getValueAt(fila, 8);
                vista.txtId.setText("" + id);
                vista.txtNombre.setText(nombre);
                vista.txtApellido.setText(apellido);
                vista.txtCuit.setText(cuit);
                vista.txtRazonSocial.setText(razonSocial);
                vista.txtTelefono.setText(telefono);
                vista.cbxPais.setSelectedItem(pais);
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
        String pais = this.vista.cbxPais.getSelectedItem().toString();

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
            String pais = this.vista.cbxPais.getSelectedItem().toString();
            String direccion = this.vista.txtDireccion.getText();
            String localidad = this.vista.txtLocalidad.getText();

            c.setId(id);
            c.setNombre(name);
            c.setApellido(apellido);
            c.setRazonSocial(razonSocial);
            c.setCuit(cuit);
            c.setTel(telefono);
            c.setPais(pais);
            c.setDireccion(direccion);
            c.setLocalidad(localidad);

            int modded = cDao.modificar(c);
            if (modded == 1) {
                JOptionPane.showMessageDialog(vista, "Cliente actualizada con exito");
            } else {
                JOptionPane.showMessageDialog(vista, "Error, no se puede actualizar el cliente");
            }
        }
    }

    public void listarClientes(JTable tabla) throws SQLException {
        // Esto es para que se ejecute la tabla al momento de iniciar el programa
        modelo = (DefaultTableModel) tabla.getModel();

        List<Cliente> lista = cDao.listarClientes();
        Object[] object = new Object[9];

        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombre();
            object[2] = lista.get(i).getApellido();
            object[3] = lista.get(i).getCuit();
            object[4] = lista.get(i).getRazonSocial();
            object[5] = lista.get(i).getTel();
            object[6] = lista.get(i).getPais();
            object[7] = lista.get(i).getDireccion();
            object[8] = lista.get(i).getLocalidad();
            modelo.addRow(object);
        }
    }

    public void delete() {
        int fila = vista.tablaCliente.getSelectedRow();
        if (fila == -1) {// de esta manera el usuario solo podra eliminar si selecciona una marca sino no
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una marca");
        } else {
            try {
                int id = Integer.parseInt((String) vista.tablaCliente.getValueAt(fila, 0).toString());
                cDao.delete(id);
                JOptionPane.showMessageDialog(vista, "Marca eliminada");
            } catch (SQLException ex) {
                Logger.getLogger(GestorMarca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void llenarCombo() throws SQLException {
        PaisDao paises = new PaisDao();
        ArrayList<Pais> listarPaises = paises.getPais();
        vista.cbxPais.removeAllItems();

        for (int i = 0; i < listarPaises.size(); i++) {
            vista.cbxPais.addItem(listarPaises.get(i).getName());
        }
    }

    void limpiarTabla() {
        for (int i = 0; i < vista.tablaCliente.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void buscarClientes(JTable tablaMarca) throws SQLException {
        // Esto es para que se ejecute la tabla al momento de iniciar el programa
        modelo = (DefaultTableModel) vista.tablaCliente.getModel();
        String name = this.vista.txtBuscar.getText();

        List<Cliente> listaClientes = cDao.buscarClientes(name);
        Object[] object = new Object[9];

        for (int i = 0; i < listaClientes.size(); i++) {
            object[0] = listaClientes.get(i).getId();
            object[1] = listaClientes.get(i).getNombre();
            object[2] = listaClientes.get(i).getApellido();
            object[3] = listaClientes.get(i).getCuit();
            object[4] = listaClientes.get(i).getRazonSocial();
            object[5] = listaClientes.get(i).getTel();
            object[6] = listaClientes.get(i).getPais();
            object[7] = listaClientes.get(i).getDireccion();
            object[8] = listaClientes.get(i).getLocalidad();
            modelo.addRow(object);
        }
    }
}
