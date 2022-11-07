/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anibal-99
 */
public class VistaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VistaPrincipal
     */
    public VistaPrincipal() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelPrincipal = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        abmcMarca = new javax.swing.JMenuItem();
        abmcCliente = new javax.swing.JMenuItem();
        abmcAuto = new javax.swing.JMenuItem();
        abmcModelos = new javax.swing.JMenuItem();
        menuVendedor = new javax.swing.JMenuItem();
        menuVenta = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout PanelPrincipalLayout = new javax.swing.GroupLayout(PanelPrincipal);
        PanelPrincipal.setLayout(PanelPrincipalLayout);
        PanelPrincipalLayout.setHorizontalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        PanelPrincipalLayout.setVerticalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/menu (1).png"))); // NOI18N
        jMenu1.setText("Menu");

        abmcMarca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Marca.png"))); // NOI18N
        abmcMarca.setText("Marcas");
        abmcMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abmcMarcaActionPerformed(evt);
            }
        });
        jMenu1.add(abmcMarca);

        abmcCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Cliente.png"))); // NOI18N
        abmcCliente.setText("Clientes");
        abmcCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abmcClienteActionPerformed(evt);
            }
        });
        jMenu1.add(abmcCliente);

        abmcAuto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Auto.png"))); // NOI18N
        abmcAuto.setText("Autos");
        abmcAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abmcAutoActionPerformed(evt);
            }
        });
        jMenu1.add(abmcAuto);

        abmcModelos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/matricula.png"))); // NOI18N
        abmcModelos.setText("Modelos");
        abmcModelos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abmcModelosActionPerformed(evt);
            }
        });
        jMenu1.add(abmcModelos);

        menuVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/empleado-con-corbata (1).png"))); // NOI18N
        menuVendedor.setText("Vendedor");
        menuVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuVendedorActionPerformed(evt);
            }
        });
        jMenu1.add(menuVendedor);

        menuVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/carrito-de-compras.png"))); // NOI18N
        menuVenta.setText("Venta");
        menuVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuVentaActionPerformed(evt);
            }
        });
        jMenu1.add(menuVenta);

        jMenuBar1.add(jMenu1);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/cerrar-sesion.png"))); // NOI18N
        jMenu6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jMenu6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jMenu6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/cerrar-el-simbolo-de-la-cruz-en-un-circulo.png"))); // NOI18N
        jMenuItem1.setText("Cerrar sesion");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem1);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void abmcAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abmcAutoActionPerformed
        VistaAuto vm = new VistaAuto();
        try {
            vm.inicializar();
        } catch (SQLException ex) {
            Logger.getLogger(VistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_abmcAutoActionPerformed

    private void abmcClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abmcClienteActionPerformed
        // TODO add your handling code here:
        VistaCliente v = new VistaCliente();
        try {
            v.inicializar();
        } catch (SQLException ex) {
            Logger.getLogger(VistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_abmcClienteActionPerformed

    private void abmcMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abmcMarcaActionPerformed
        VistaMarca vm = new VistaMarca();
        try {
            vm.inicializar();
        } catch (SQLException ex) {
            Logger.getLogger(VistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_abmcMarcaActionPerformed

    private void abmcModelosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abmcModelosActionPerformed
        // TODO add your handling code here:
        VistaModelo vm = new VistaModelo();
        try {
            vm.inicializar();
        } catch (SQLException ex) {
            Logger.getLogger(VistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_abmcModelosActionPerformed

    private void menuVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuVendedorActionPerformed
        // TODO add your handling code here:
        VistaVendedor v = new VistaVendedor();
        try {
            v.inicializar();
        } catch (SQLException ex) {
            Logger.getLogger(VistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_menuVendedorActionPerformed

    private void menuVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuVentaActionPerformed
        // TODO add your handling code here:
        VistaVenta v = new VistaVenta();
        try {
            v.inicializar();
        } catch (SQLException ex) {
            Logger.getLogger(VistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuVentaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaPrincipal().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JDesktopPane PanelPrincipal;
    private javax.swing.JMenuItem abmcAuto;
    private javax.swing.JMenuItem abmcCliente;
    private javax.swing.JMenuItem abmcMarca;
    private javax.swing.JMenuItem abmcModelos;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem menuVendedor;
    private javax.swing.JMenuItem menuVenta;
    // End of variables declaration//GEN-END:variables
}
