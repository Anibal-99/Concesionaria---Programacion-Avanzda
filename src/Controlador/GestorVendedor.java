/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelos.Pais;
import Modelos.PaisDao;
import Modelos.Vendedor;
import Modelos.VendedorDao;
import Vistas.VistaVendedor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anibal-99
 */
public class GestorVendedor implements ActionListener {

    public GestorVendedor() {
    }
    VistaVendedor vista = new VistaVendedor();
    VendedorDao vDao = new VendedorDao();
    Vendedor vendedor = new Vendedor();
    DefaultTableModel modelo = new DefaultTableModel();

    public GestorVendedor(VistaVendedor v) {
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
        if (e.getSource() == this.vista.btnAgregar) {
            this.agregar();
            limpiarTabla();
            try {
                this.listarVendedor(vista.tableVendedor);
            } catch (SQLException ex) {
                Logger.getLogger(GestorVendedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == this.vista.btnModificar) {
            this.modificar();
        }
        if (e.getSource() == this.vista.btnListar) {
            this.limpiarTabla();
            try {
                this.listarVendedor(vista.tableVendedor);
            } catch (SQLException ex) {
                Logger.getLogger(GestorVendedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == this.vista.btnActualizar) {
            this.actualizar();
            this.limpiarTabla();
            try {
                listarVendedor(this.vista.tableVendedor);
            } catch (SQLException ex) {
                Logger.getLogger(GestorVendedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == vista.btnEliminar) {
            this.delete();
            this.limpiarTabla();
            try {
                listarVendedor(this.vista.tableVendedor);
            } catch (SQLException ex) {
                Logger.getLogger(GestorVendedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(e.getSource()==this.vista.btnBuscar){       
            this.limpiarTabla();
            try {
                this.buscarVendedores(this.vista.tableVendedor);
            } catch (SQLException ex) {
                Logger.getLogger(GestorVendedor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    public void agregar() {
        String nombre = this.vista.txtNombre.getText();
        String apellido = this.vista.txtApellido.getText();
        Pais pais = ((Pais) this.vista.cbxPais.getSelectedItem());
        String dni = this.vista.txtDni.getText();

        vendedor.setNombre(nombre);
        vendedor.setApellido(apellido);
        vendedor.setPais(pais);
        vendedor.setDni(dni);

        if (this.vista.txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se puede agregar sin ingresar todos los datos");
        } else {
            try {
                vDao.agregar(vendedor);
                JOptionPane.showMessageDialog(null, "Vendedor se agrego con exito");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se agregaron los datos");
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
        for (int i = 0; i < vista.tableVendedor.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void modificar() {
        vista.txtId.setEnabled(false);
        int fila = vista.tableVendedor.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
        } else {
            int id = Integer.parseInt((String) vista.tableVendedor.getValueAt(fila, 0).toString());
            String nombre = (String) vista.tableVendedor.getValueAt(fila, 1);
            String apellido = (String) vista.tableVendedor.getValueAt(fila, 2);
            String dni = (String) vista.tableVendedor.getValueAt(fila, 3);
            Pais pais = (Pais) vista.tableVendedor.getValueAt(fila, 4);

            vista.txtId.setText("" + id);
            vista.txtNombre.setText(nombre);
            vista.txtApellido.setText(apellido);
            vista.txtDni.setText(dni);
            DefaultComboBoxModel<Pais> cbxModel = ((DefaultComboBoxModel) vista.cbxPais.getModel());
            cbxModel.setSelectedItem(pais);
        }
    }

    public void listarVendedor(JTable tabla) throws SQLException {
        // Esto es para que se ejecute la tabla al momento de iniciar el programa
        modelo = (DefaultTableModel) tabla.getModel();

        List<Vendedor> lista = vDao.listarVendedor();

        for (Vendedor v : lista) {
            Object[] object = {v.getId(), v.getNombre(), v.getApellido(), v.getDni(), v.getPais()};
            modelo.addRow(object);
        }
    }

    public void actualizar() {

        if (vista.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(vista, "No se Identifica el Id debe selecionar la opcion Editar");
        } else {
            int id = Integer.parseInt(vista.txtId.getText());
            String nombre = this.vista.txtNombre.getText();
            String apellido = this.vista.txtApellido.getText();
            Pais pais = ((Pais) this.vista.cbxPais.getSelectedItem());
            String dni = this.vista.txtDni.getText();

            vendedor.setNombre(nombre);
            vendedor.setApellido(apellido);
            vendedor.setPais(pais);
            vendedor.setDni(dni);
            vendedor.setId(id);

            int modded = vDao.actualizar(vendedor);
            if (modded == 1) {
                JOptionPane.showMessageDialog(vista, "Vendedor actualizada con exito");
            } else {
                JOptionPane.showMessageDialog(vista, "Error, no se puede actualizar el cliente");
            }
        }
    }

    public void delete() {
        int fila = vista.tableVendedor.getSelectedRow();
        if (fila == -1) {// de esta manera el usuario solo podra eliminar si selecciona una marca sino no
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una marca");
        } else {
            try {
                int id = Integer.parseInt((String) vista.tableVendedor.getValueAt(fila, 0).toString());
                vDao.delete(id);
                JOptionPane.showMessageDialog(vista, "Marca eliminada");
            } catch (SQLException ex) {
                Logger.getLogger(GestorMarca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void buscarVendedores(JTable tablaVendedor) throws SQLException {
        // Esto es para que se ejecute la tabla al momento de iniciar el programa
        modelo = (DefaultTableModel) vista.tableVendedor.getModel();
        String name = this.vista.txtBuscar.getText();
        List<Vendedor> vendedores= vDao.buscarVendedores(name);

        for (Vendedor v : vendedores) {
            Object[] object = {v.getId(), v.getNombre(), v.getApellido(), v.getDni(), v.getPais()};
            modelo.addRow(object);
        }
    }

}
