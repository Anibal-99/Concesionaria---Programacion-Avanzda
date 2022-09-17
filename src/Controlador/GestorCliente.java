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

    public GestorCliente(VistaCliente v) throws SQLException {
        this.vista = v;
        this.vista.btnAgregar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnAgregar) {
            agregar();
        }
    }

    public void agregar() {
        //Conexion conectar = new Conexion();
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

    public void llenarCombo() throws SQLException {
        PaisDao paises = new PaisDao();
        ArrayList<Pais> listarPaises = paises.getPais();
        vista.cbxPais.removeAllItems();

        for (int i = 0; i < listarPaises.size(); i++) {
            vista.cbxPais.addItem(listarPaises.get(i).getName());
        }
    }
}
