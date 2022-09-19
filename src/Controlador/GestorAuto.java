/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.DefaultListCellRenderer;

import java.awt.event.ActionEvent;
import Modelos.AutoDAO;
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaAuto.ListarjButton) {
            listar(vistaAuto.AutosjTable);
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
