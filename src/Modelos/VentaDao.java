/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Anibal-99
 */
public class VentaDao {

    PreparedStatement insert;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    Venta v = new Venta();

    public int agregar(Venta venta) {
        System.out.println("Llego");
        String sql = ("INSERT INTO venta(fecha_venta,auto_id,cliente_id,vendedor_id,monto_total,impuesto,cantidad)values(?,?,?,?,?,?,?)");
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sql);
            
            insert.setString(1, venta.getFecha());
            insert.setInt(2, venta.getAuto().getId());
            insert.setInt(3, venta.getCliente().getId());
            insert.setInt(4, venta.getVendedor().getId());
            insert.setFloat(5, venta.getMontoTotal());
            insert.setFloat(6, venta.getImpuesto());
            insert.setInt(7, venta.getCantidad());
            System.out.println(insert);
            insert.executeUpdate();
            
        } catch (Exception e) {

        }
        return 1;
    }
}
