/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelos.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import Vistas.VistaModelo;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anibal-99
 */
public class GestorModelo implements ActionListener {

    VistaModelo vista = new VistaModelo();
    ModeloDAO modeloDao = new ModeloDAO();
    Modelo m = new Modelo();
    DefaultTableModel modelo = new DefaultTableModel();

    public GestorModelo() {

    }

    public GestorModelo(VistaModelo vista) throws SQLException {
        this.vista = vista;
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnListar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnAgregar) {
            this.agregar();
            limpiarTabla();
            try {
                listarModelos(vista.tablaModelo);
            } catch (SQLException ex) {
                Logger.getLogger(GestorModelo.class.getName()).log(Level.SEVERE, null, ex);
            }
            nuevo();
        }
        if (e.getSource() == vista.btnModificar) {
            this.modificar();
        }
        if (e.getSource() == vista.btnListar) {
            limpiarTabla();
            try {
                listarModelos(vista.tablaModelo);
            } catch (SQLException ex) {
                Logger.getLogger(GestorModelo.class.getName()).log(Level.SEVERE, null, ex);
            }
            nuevo();
        }
        if (e.getSource() == vista.btnActualizar) {
            this.actualizar();
            limpiarTabla();
            try {
                listarModelos(vista.tablaModelo);
            } catch (SQLException ex) {
                Logger.getLogger(GestorModelo.class.getName()).log(Level.SEVERE, null, ex);
            }
            nuevo();
        }
        if (e.getSource() == vista.btnEliminar) {
            try {
                this.delete();
                limpiarTabla();
                listarModelos(vista.tablaModelo);
            } catch (SQLException ex) {
                Logger.getLogger(GestorModelo.class.getName()).log(Level.SEVERE, null, ex);
            }
            nuevo();
        }
        if (e.getSource() == vista.btnBuscar) {
            limpiarTabla();
            try {
                busrcarModelos(vista.tablaModelo);
            } catch (SQLException ex) {
                Logger.getLogger(GestorModelo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void agregar() {
        String nombre = this.vista.txtNombre.getText();
        Marca marca = (Marca) this.vista.cbxCombo.getSelectedItem();
        if (this.vista.txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se puedo agregar sin ingresar los datos");
        } else {
            try {
                int anio = Integer.parseInt(vista.txtAnio.getText());
                m.setNombre(nombre);
                m.setAnio(anio);
                m.setMarca(marca);
                modeloDao.agregar(m);
                JOptionPane.showMessageDialog(null, "Modelo se agrego con exito");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se agregaron los datos");
            }
        }
    }

    public void llenarCombo() throws SQLException {
        MarcaDao marcas = new MarcaDao();
        ArrayList<Marca> listarMarcas = marcas.getMarcas();
        DefaultComboBoxModel<Marca> cbxModel = ((DefaultComboBoxModel) vista.cbxCombo.getModel());
        vista.cbxCombo.removeAllItems();

        for (int i = 0; i < listarMarcas.size(); i++) {
            cbxModel.addElement(listarMarcas.get(i));
        }
    }

    void nuevo() {
        vista.txtId.setText("");
        vista.txtNombre.setText("");
        vista.txtAnio.setText("");
        vista.txtBuscar.setText("");
    }

    public void modificar() {
        int fila = vista.tablaModelo.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
        } else {
            int id = Integer.parseInt((String) vista.tablaModelo.getValueAt(fila, 0).toString());
            Marca marca = (Marca) vista.tablaModelo.getValueAt(fila, 1);
            String name = (String) vista.tablaModelo.getValueAt(fila, 2);
            int anio = Integer.parseInt((String) vista.tablaModelo.getValueAt(fila, 3).toString());
            vista.txtId.setText("" + id);
            vista.txtNombre.setText(name);
            DefaultComboBoxModel<Marca> cbxModel = ((DefaultComboBoxModel) vista.cbxCombo.getModel());
            cbxModel.setSelectedItem(marca);
            vista.txtAnio.setText("" + anio);
        }
    }

    public void listarModelos(JTable tabla) throws SQLException {
        // Esto es para que se ejecute la tabla al momento de iniciar el programa
        modelo = (DefaultTableModel) tabla.getModel();
        List<Modelo> lista = modeloDao.getListarModelos();

        for (Modelo mod : lista) {
            Object[] object = {mod.getId(), mod.getMarca(), mod.getNombre(), mod.getAnio()};
            modelo.addRow(object);
        }
    }

    public void actualizar() {
        if (vista.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(vista, "No se Identifica el Id debe selecionar la opcion Editar");
        } else {
            int id = Integer.parseInt(vista.txtId.getText());
            String name = this.vista.txtNombre.getText();
            Marca marca = (Marca) this.vista.cbxCombo.getSelectedItem();
            int anio = Integer.parseInt(this.vista.txtAnio.getText());
            m.setId(id);
            m.setNombre(name);
            m.setMarca(marca);
            m.setAnio(anio);
            int modded = modeloDao.actualizar(m);
            if (modded == 1) {
                JOptionPane.showMessageDialog(vista, "Marca actualizada con exito");
            } else {
                JOptionPane.showMessageDialog(vista, "Error, no se actualizo la marca");
            }
        }
    }

    void limpiarTabla() {
        for (int i = 0; i < vista.tablaModelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void delete() {
        int fila = vista.tablaModelo.getSelectedRow();
        if (fila == -1) {// de esta manera el usuario solo podra eliminar si selecciona una marca sino no
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una marca");
        } else {
            try {
                int id = Integer.parseInt((String) vista.tablaModelo.getValueAt(fila, 0).toString());
                modeloDao.delete(id);
                JOptionPane.showMessageDialog(vista, "Marca eliminada");
            } catch (Exception ex) {
                Logger.getLogger(GestorMarca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void busrcarModelos(JTable tabla) throws SQLException {
        // Esto es para que se ejecute la tabla al momento de iniciar el programa
        modelo = (DefaultTableModel) tabla.getModel();
        String name = this.vista.txtBuscar.getText();

        List<Modelo> lista = modeloDao.buscarModelos(name);
        for (Modelo mod : lista) {
            Object[] object = {mod.getId(), mod.getMarca(), mod.getNombre(), mod.getAnio()};
            modelo.addRow(object);
        }

    }

}
