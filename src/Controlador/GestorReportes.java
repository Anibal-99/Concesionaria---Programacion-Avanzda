/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import Vistas.VistaReporte;

import java.awt.event.ActionListener;
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
            generarReportes();
        }
    }
    
    public void generarReportes(){
        int num = Integer.parseInt(this.vista.txtNumero.getText());
        switch(num){
            case 1:
                System.out.println("hasta aca todo bien al 1");
            break;
            
            case 2:
                System.out.println("hasta aca todo bien al 2");
            break;
            
            case 3:
                System.out.println("hasta aca todo bien al 3");
            break;
        }
        if(num>3){
            JOptionPane.showMessageDialog(null, "Debe ingresar un numero comprendido entre 1 y 3");
            this.vista.txtNumero.setText("");
        }
    }
}
