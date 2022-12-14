/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vistas.VistaMarca;
import Modelos.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import static Utils.StringUtils.capitalize;
/**
 *
 * @author Anibal-99
 */
public class GestorMarca implements ActionListener {

    public GestorMarca() {

    }
    VistaMarca vista = new VistaMarca();
    MarcaDao mDao = new MarcaDao();
    Marca m = new Marca();
    DefaultTableModel modelo = new DefaultTableModel();

    public GestorMarca(VistaMarca v) throws SQLException {
        this.vista = v;
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnListar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnFiltrar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnAgregar) {
            try {
                this.agregar();
            } catch (SQLException e1) {}
            limpiarTabla();
            try {
                buscarMarcas(vista.tablaMarca);
            } catch (SQLException ex) {
                Logger.getLogger(GestorMarca.class.getName()).log(Level.SEVERE, null, ex);
            }
            nuevo();
            this.vista.btnAgregar.setEnabled(true);
        }

        if (e.getSource() == vista.btnListar) {
            limpiarTabla();
            try {
                buscarMarcas(vista.tablaMarca);
            } catch (SQLException ex) {
                Logger.getLogger(GestorMarca.class.getName()).log(Level.SEVERE, null, ex);
            }
            nuevo();
        }
        if (e.getSource() == vista.btnModificar) {
            int fila = vista.tablaMarca.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
            } else {
                this.vista.btnAgregar.setEnabled(false);
                int id = Integer.parseInt((String) vista.tablaMarca.getValueAt(fila, 0).toString());
                String name = (String) vista.tablaMarca.getValueAt(fila, 1);
                Pais pais = ((Pais) vista.tablaMarca.getValueAt(fila, 2));
                String obs = (String) vista.tablaMarca.getValueAt(fila, 3);
                vista.txtId.setText("" + id);
                vista.txtname.setText(name);
                DefaultComboBoxModel<Pais> cbxModel = ((DefaultComboBoxModel) vista.cbxCombo.getModel());
                cbxModel.setSelectedItem(pais);
                vista.txtobs.setText(obs);
            }
        }
        if (e.getSource() == vista.btnActualizar) {
            try {
                actualizar();
            } catch (SQLException ex) {
                Logger.getLogger(GestorMarca.class.getName()).log(Level.SEVERE, null, ex);
            }
            limpiarTabla();
            try {
                buscarMarcas(vista.tablaMarca);
            } catch (SQLException ex) {
                Logger.getLogger(GestorMarca.class.getName()).log(Level.SEVERE, null, ex);
            }
            nuevo();
            this.vista.btnAgregar.setEnabled(true);
        }
        if (e.getSource() == vista.btnEliminar) {
            try {
                delete();
                limpiarTabla();
                buscarMarcas(vista.tablaMarca);
            } catch (SQLException ex) {
                Logger.getLogger(GestorMarca.class.getName()).log(Level.SEVERE, null, ex);
            }
            nuevo();
        }
        if (e.getSource() == vista.btnFiltrar) {
            limpiarTabla();
            try {
                filtrarMarca(vista.tablaMarca);
            } catch (SQLException ex) {
                Logger.getLogger(GestorMarca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void delete() {
        int fila = vista.tablaMarca.getSelectedRow();
        if (fila == -1) {// de esta manera el usuario solo podra eliminar si selecciona una marca sino no
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una marca");
        } else {
            try {
                int id = Integer.parseInt((String) vista.tablaMarca.getValueAt(fila, 0).toString());
                mDao.delete(id);
                JOptionPane.showMessageDialog(vista, "Marca eliminada");
            } catch (SQLException ex) {
                Logger.getLogger(GestorMarca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    void nuevo() {
        vista.txtId.setText("");
        vista.txtname.setText("");
        vista.txtobs.setText("");
    }

    public void actualizar() throws SQLException {

        if (vista.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(vista, "No se Identifica el Id debe selecionar la opcion Editar");
        } else {
            int id = Integer.parseInt(vista.txtId.getText());
            String name = this.vista.txtname.getText();
            Pais pais = ((Pais) this.vista.cbxCombo.getSelectedItem());
            String obs = this.vista.txtobs.getText();
            m.setId(id);
            m.setName(name);
            m.setPais(pais);
            m.setObs(obs);
            int modded = mDao.modificar(m);
            if (modded == 1) {
                JOptionPane.showMessageDialog(vista, "Marca actualizada con exito");
            } else {
                JOptionPane.showMessageDialog(vista, "Error, no se actualizo la marca");
            }
        }
    }
    //Connection con;
    //PreparedStatement insert;

    public void agregar() throws SQLException {
        String name = this.vista.txtname.getText();
        Pais pais = ((Pais) this.vista.cbxCombo.getSelectedItem());
        String obs = this.vista.txtobs.getText();
        m.setName(name);
        m.setPais(pais);
        m.setObs(obs);

        if (this.vista.txtname.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se puedo agregar sin ingresar los datos");
        }
        int flag = mDao.agregar(m);
        if (flag == 1){
            JOptionPane.showMessageDialog(null, "Marca se agrego con exito");
        } else {
            JOptionPane.showMessageDialog(null, "No se agregaron los datos");
        }
    }

    public void buscarMarcas(JTable tabla) throws SQLException {
        modelo = (DefaultTableModel) tabla.getModel();
        List<Marca> lista = mDao.listarMarcas();

        for (Marca mar : lista) {
            Object[] object = {mar.getId(), mar.getName(), mar.getPais(), mar.getObs()};
            modelo.addRow(object);
        }
    }

    void limpiarTabla() {
        for (int i = 0; i < vista.tablaMarca.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void filtrarMarca(JTable tablaMarca) throws SQLException {
        // Esto es para que se ejecute la tabla al momento de iniciar el programa
        modelo = (DefaultTableModel) tablaMarca.getModel();
        String valor = capitalize(this.vista.txtFiltrar.getText());
        List<Marca> lista = mDao.filtrarMarcas(valor);

        for (Marca mar : lista) {
            Object[] object = {mar.getId(), mar.getName(), mar.getPais(), mar.getObs()};
            modelo.addRow(object);
        }
    }

    public void llenarCombo() throws SQLException {
        PaisDao paises = new PaisDao();
        ArrayList<Pais> listarPaises = paises.getPais();

        DefaultComboBoxModel<Pais> cbxModel = ((DefaultComboBoxModel) vista.cbxCombo.getModel());
        vista.cbxCombo.removeAllItems();

        for (int i = 0; i < listarPaises.size(); i++) {
            cbxModel.addElement(listarPaises.get(i));
        }
    }
}
