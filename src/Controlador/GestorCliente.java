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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnAgregar) {
            agregar();
        }
        if (e.getSource() == vista.btnListar) {
            try {
                listarClientes(vista.tablaCliente);
            } catch (SQLException ex) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void agregar() {
        // Conexion conectar = new Conexion();
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
            String direccion = this.vista.txtDireccion.getText();
            String localidad = this.vista.txtLocalidad.getText();
            String pais = this.vista.cbxPais.getSelectedItem().toString();
            c.setId(id);
            c.setNombre(name);
            c.setApellido(apellido);
            c.setRazonSocial(razonSocial);
            c.setCuit(cuit);
            c.setTel(telefono);
            c.setDireccion(direccion);
            c.setLocalidad(localidad);

            int modded = cDao.modificar(c);
            if (modded == 1) {
                JOptionPane.showMessageDialog(vista, "Marca actualizada con exito");
            } else {
                JOptionPane.showMessageDialog(vista, "Error, no se actualizo la marca");
            }
        }
    }

    public void listarClientes(JTable tabla) throws SQLException {
        // Esto es para que se ejecute la tabla al momento de iniciar el programa
        modelo = (DefaultTableModel) tabla.getModel();

        List<Cliente> lista = cDao.listarClientes();
        //System.out.println(lista);
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
        // vista.tablaMarca.setModel(modelo);
    }

    public void llenarCombo() throws SQLException {
        PaisDao paises = new PaisDao();
        ArrayList<Pais> listarPaises = paises.getPais();
        vista.cbxPais.removeAllItems();

        for (int i = 0; i < listarPaises.size(); i++) {
            vista.cbxPais.addItem(listarPaises.get(i).getName());
        }
    }
}
