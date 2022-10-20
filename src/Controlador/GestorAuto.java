/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.awt.event.ActionListener;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.DefaultListCellRenderer;

import java.awt.event.ActionEvent;
import Modelos.AutoDAO;
import Modelos.Modelo;
import Modelos.ModeloDAO;
import Modelos.Auto;
import Modelos.ColorDao;
import Vistas.VistaAuto;
import Vistas.NumberRenderer;
import Modelos.Color;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Anibal-99
 */
public class GestorAuto implements ActionListener {

    AutoDAO autoDAO = new AutoDAO();
    Auto auto = new Auto();
    VistaAuto vistaAuto = new VistaAuto();
    DefaultTableModel defaultTableModel = new DefaultTableModel();

    public GestorAuto(VistaAuto vistaAuto) {
        this.vistaAuto = vistaAuto;
        this.vistaAuto.ListarjButton1.addActionListener(this);
        this.vistaAuto.AgregarjButton.addActionListener(this);
        this.vistaAuto.EliminarjButton.addActionListener(this);
        this.vistaAuto.ModificarjButton.addActionListener(this);
        this.vistaAuto.ActualizarjButton.addActionListener(this);
        this.vistaAuto.btnNuevo.addActionListener(this);
        this.vistaAuto.btnBuscar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaAuto.ListarjButton1) {
            this.limpiarTabla();
            try {
                this.listar(vistaAuto.AutosjTable);
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(GestorAuto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            nuevo();
        } else if (e.getSource() == vistaAuto.AgregarjButton) {
            this.agregar();
            this.limpiarTabla();
            try {
                this.listar(vistaAuto.AutosjTable);
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(GestorAuto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            nuevo();
        } else if (e.getSource() == vistaAuto.EliminarjButton) {
            this.eliminar();
            this.limpiarTabla();
            try {
                this.listar(vistaAuto.AutosjTable);
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(GestorAuto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            nuevo();
        } else if (e.getSource() == vistaAuto.ModificarjButton) {
            this.modificar();
        } else if (e.getSource() == vistaAuto.ActualizarjButton) {
            try {
                this.actualizar();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(GestorAuto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            nuevo();
        }
        if (e.getSource() == vistaAuto.btnNuevo) {
            nuevo();
        }
        if (e.getSource() == vistaAuto.btnBuscar) {
            limpiarTabla();
            try {
                this.buscarAutos(vistaAuto.AutosjTable);
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(GestorAuto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }

    public void modificar() {
        vistaAuto.IDjTextField.setEnabled(false);
        int fila = vistaAuto.AutosjTable.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaAuto, "Debe seleccionar una fila");
        } else {
            int id = Integer.parseInt((String) vistaAuto.AutosjTable.getValueAt(fila, 0).toString());
            Modelo modelo =  (Modelo) vistaAuto.AutosjTable.getValueAt(fila, 1);
            String precio = (String) vistaAuto.AutosjTable.getValueAt(fila, 2).toString();
            String observacion = (String) vistaAuto.AutosjTable.getValueAt(fila, 3);
            Color color = (Color) vistaAuto.AutosjTable.getValueAt(fila, 4);

            vistaAuto.IDjTextField.setText("" + id);
            vistaAuto.PreciojTextField.setText("" + precio);
            vistaAuto.jTextArea1.setText(observacion);
            DefaultComboBoxModel<Color> cbxcolor = ((DefaultComboBoxModel) vistaAuto.cbxColor.getModel());
            DefaultComboBoxModel<Modelo> cbxModel = ((DefaultComboBoxModel) vistaAuto.ModelojComboBox.getModel());
            cbxcolor.setSelectedItem(color);
            cbxModel.setSelectedItem(modelo);
        }
    }

    public void actualizar() throws SQLException {
        if (vistaAuto.IDjTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(vistaAuto, "No se Identifica el Id debe selecionar la opcion Editar");
        } else {
            int id = Integer.parseInt(vistaAuto.IDjTextField.getText());
            Modelo modelo = (Modelo) this.vistaAuto.ModelojComboBox.getSelectedItem();
            Float precio = Float.parseFloat((String) vistaAuto.PreciojTextField.getText());
            String observacion = this.vistaAuto.jTextArea1.getText();
            Color color = (Color) this.vistaAuto.cbxColor.getSelectedItem();

            auto.setId(id);
            auto.setModelo(modelo);
            auto.setPrecio(precio);
            auto.setObservacion(observacion);
            auto.setColor(color);

            int flag = autoDAO.actualizar(auto);
            if (flag == 1) {
                this.limpiarTabla();
                this.listar(vistaAuto.AutosjTable);
                JOptionPane.showMessageDialog(vistaAuto, "Auto actualizado con exito");
            } else {
                JOptionPane.showMessageDialog(vistaAuto, "Error, no se actualizo el auto");
            }
        }

    }

    public void agregar() {
        // String modelo = this.vistaAuto.ModelojComboBox.getSelectedItem().toString();
        // int modelo_id = this.vistaAuto.ModelojComboBox.getSelectedIndex() + 1;

        Modelo modelo = ((Modelo) this.vistaAuto.ModelojComboBox.getSelectedItem());
        float precio = Float.parseFloat(this.vistaAuto.PreciojTextField.getText());
        String observacion = this.vistaAuto.jTextArea1.getText();
        Color color = (Color) this.vistaAuto.cbxColor.getSelectedItem();

        auto.setModelo(modelo);
        auto.setPrecio(precio);
        auto.setObservacion(observacion);
        auto.setColor(color);

        if (this.vistaAuto.ModelojComboBox.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se puedo agregar sin ingresar los datos");
        } else {
            try {
                autoDAO.agregar(auto);
                this.limpiarTabla();
                this.listar(vistaAuto.AutosjTable);
                JOptionPane.showMessageDialog(null, "Marca se agrego con exito");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se agregaron los datos");
            }
        }
    }

    public void eliminar() {
        int fila = vistaAuto.AutosjTable.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaAuto, "Debe seleccionar un auto");
        } else {
            try {
                int id = Integer.parseInt((String) vistaAuto.AutosjTable.getValueAt(fila, 0).toString());
                autoDAO.eliminar(id);
                JOptionPane.showMessageDialog(vistaAuto, "Auto eliminado");
            } catch (SQLException ex) {

            }
        }
    }



    public void listar(JTable tablaAutos) throws SQLException{
        defaultTableModel = (DefaultTableModel) tablaAutos.getModel();
        List<Auto> autos = autoDAO.listar();

        for (Auto a : autos) {
            Object[] object = {a.getId(), a.getModelo(), a.getPrecio(), a.getObservacion(), a.getColor()};
            defaultTableModel.addRow(object);
        }

        DefaultTableCellRenderer renderLeft = new DefaultTableCellRenderer();
        vistaAuto.AutosjTable.setModel(defaultTableModel);
        TableColumnModel tcm = vistaAuto.AutosjTable.getColumnModel();
        vistaAuto.AutosjTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tcm.getColumn(0).setCellRenderer(renderLeft);
        tcm.getColumn(0).setPreferredWidth(30);
        tcm.getColumn(1).setPreferredWidth(150);
        tcm.getColumn(2).setPreferredWidth(120);
        tcm.getColumn(3).setPreferredWidth(450);
        tcm.getColumn(2).setCellRenderer(NumberRenderer.getCurrencyRenderer());
    }

    void limpiarTabla() {
        for (int i = 0; i < vistaAuto.AutosjTable.getRowCount(); i++) {
            defaultTableModel.removeRow(i);
            i = i - 1;
        }
    }

    public void llenarComboColor() throws SQLException {
        ColorDao colores = new ColorDao();
        ArrayList<Color> listarColores = colores.getColor();
        DefaultComboBoxModel<Color> cbxColor = ((DefaultComboBoxModel) vistaAuto.cbxColor.getModel());
        vistaAuto.cbxColor.removeAllItems();

        for (int i = 0; i < listarColores.size(); i++) {
            cbxColor.addElement(listarColores.get(i));
        }
    }

    public void llenarCombo() throws SQLException {
        ModeloDAO modeloDao = new ModeloDAO();
        ArrayList<Modelo> modelos = modeloDao.getModelos();
        DefaultComboBoxModel<Modelo> cbxModel = ((DefaultComboBoxModel) vistaAuto.ModelojComboBox.getModel());
        vistaAuto.ModelojComboBox.removeAllItems();

        for (int i = 0; i < modelos.size(); i++) {
            cbxModel.addElement(modelos.get(i));
        }
    }

    void nuevo() {
        vistaAuto.IDjTextField.setText("");
        vistaAuto.PreciojTextField.setText("");
        vistaAuto.jTextArea1.setText("");
    }

    public void buscarAutos(JTable tablaAuto) throws SQLException {
        // Esto es para que se ejecute la tabla al momento de iniciar el programa
        defaultTableModel = (DefaultTableModel) tablaAuto.getModel();
        String name = this.vistaAuto.txtBuscar.getText();
        List<Auto> autos = autoDAO.buscarAutos(name);

        for (Auto a : autos) {
            Object[] object = {a.getId(), a.getModelo(), a.getPrecio(), a.getObservacion(), a.getColor()};
            defaultTableModel.addRow(object);
        }
    }
}
