/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import Vistas.VistaAuto;
import Vistas.NumberRenderer;

/**
 *
 * @author Anibal-99
 */
public class GestorAuto implements ActionListener{
    AutoDAO autoDAO = new AutoDAO();
    Auto auto = new Auto();
    VistaAuto vistaAuto = new VistaAuto();
    DefaultTableModel defaultTableModel = new DefaultTableModel();

    public GestorAuto(VistaAuto vistaAuto) {
        this.vistaAuto = vistaAuto;
        this.vistaAuto.ListarjButton.addActionListener(this);
        this.vistaAuto.AgregarjButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // JButton btnListar = vistaAuto.ListarjButton;
        // switch(e.getSource()){
        //     case btnListar:
        //         this.listar(vistaAuto.AutosjTable);
        //     case vistaAuto.AgregarjButton:
        //         this.agregar();
        //     default:
        //         //
        // }
        if (e.getSource() == vistaAuto.ListarjButton){
            this.listar(vistaAuto.AutosjTable);
        } else if (e.getSource() == vistaAuto.AgregarjButton) {
            this.agregar();
        }
    }

    public void agregar() {
        // int id = Integer.parseInt(this.vistaAuto.IDjTextField.getText());
        // System.out.println(id);
        String modelo = this.vistaAuto.ModelojComboBox.getSelectedItem().toString();
        // System.out.println(modelo);
        int modelo_id = this.vistaAuto.ModelojComboBox.getSelectedIndex() + 1;
        // System.out.println(modelo_id);
        float precio = Float.parseFloat(this.vistaAuto.PreciojTextField.getText());
        // System.out.println(precio);
        String observacion = this.vistaAuto.jTextArea1.getText();
        // System.out.println(observacion);
        // auto.setId(id);
        auto.setModelo(modelo);
        auto.setPrecio(precio);
        auto.setObservacion(observacion);

        if (this.vistaAuto.ModelojComboBox.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se puedo agregar sin ingresar los datos");
        } else {
            try {
                autoDAO.agregar(auto, modelo_id);
                JOptionPane.showMessageDialog(null, "Marca se agrego con exito");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se agregaron los datos");
            }
        }
    }

    public void llenarCombo() throws SQLException{
        ModeloDAO modelos = new ModeloDAO();
        ArrayList<Modelo> listarModelos = modelos.getModelo();
        vistaAuto.ModelojComboBox.removeAllItems();

        for (int i=0; i<listarModelos.size(); i++){
            vistaAuto.ModelojComboBox.addItem(
                listarModelos.get(i).getMarca() + " " + listarModelos.get(i).getNombre() + " " + listarModelos.get(i).getAnio()
            );
        }
    }

    public void listar(JTable tablaAutos){
        defaultTableModel = (DefaultTableModel)tablaAutos.getModel();
        List<Auto>autos = AutoDAO.listar();
        Object[]object = new Object[4];
        for (int i = 0; i < autos.size(); i++) {
            object[0] = autos.get(i).getId();
            object[1] = autos.get(i).getModelo();
            object[2] = autos.get(i).getPrecio();
            object[3] = autos.get(i).getObservacion();
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
}
