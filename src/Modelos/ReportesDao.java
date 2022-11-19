/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import com.itextpdf.text.log.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Anibal-99
 */
public class ReportesDao {

    PreparedStatement insert;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();

    public ArrayList<Venta> generarReporteAuto(String valorOrden, int valorAnio) throws SQLException {
        ArrayList<Venta> data = new ArrayList<>();
        AutoDAO autoDao = new AutoDAO();
        ClienteDao clienteDao = new ClienteDao();
        VendedorDao vendedorDao = new VendedorDao();
        String sql = "select venta.id, venta.fecha_venta, venta.auto_id, venta.cliente_id, venta.vendedor_id, venta.monto_total,venta.impuesto,venta.cantidad from venta where extract(year from to_date(venta.fecha_venta, 'DD/MM/YYYY'))=? order by ? desc";
        
        con = conectar.getConection();
        insert = con.prepareStatement(sql);
        insert.setInt(1, valorAnio);
        insert.setString(2, valorOrden);
        rs = insert.executeQuery();

        while (rs.next()) {
            Venta v = new Venta();
            v.setId(rs.getInt(1));
            v.setFecha(rs.getString(2));
            v.setAuto(autoDao.getAutoById(rs.getInt(3)));
            v.setCliente(clienteDao.getClienteById(rs.getInt(4)));
            v.setVendedor(vendedorDao.getVendedorById(rs.getInt(5)));
            v.setMontoTotal(rs.getInt(6));
            v.setImpuesto(rs.getInt(7));
            v.setCantidad(rs.getInt(8));
            data.add(v);
        }
        return data;
    }
}
