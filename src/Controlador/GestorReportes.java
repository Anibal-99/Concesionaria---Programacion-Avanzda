/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import Vistas.VistaReporte;
import Vistas.VistaReporteAuto;

import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Anibal-99
 */
public class GestorReportes implements ActionListener {

    public GestorReportes() {

    }
    VistaReporte vista = new VistaReporte();

    public GestorReportes(VistaReporte v) throws SQLException {
        this.vista = v;
        this.vista.btnOk.addActionListener(this);

    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.vista.btnOk){
            try {
                generarReportes();
            } catch (SQLException ex) {
                Logger.getLogger(GestorReportes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void generarReportes() throws SQLException{
        int num = Integer.parseInt(this.vista.txtNumero.getText());

        switch(num){
            case 1:
                VistaReporteAuto vistaAuto= new VistaReporteAuto();
                GestorReporteAuto  gestorAuto = new GestorReporteAuto(vistaAuto);
                vistaAuto.setVisible(true);
            break;
            case 2:
            break;

            case 3:
            break;
        }
        if(num>3){
            JOptionPane.showMessageDialog(null, "Debe ingresar un numero comprendido entre 1 y 3");
            this.vista.txtNumero.setText("");
        }
    }
}
