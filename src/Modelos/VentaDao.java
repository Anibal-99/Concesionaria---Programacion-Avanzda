/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

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

    public int agregar(Venta venta) throws SQLException {
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
            insert.executeUpdate();

        } catch (Exception e) {

        }
        con.close();
        return 1;
    }

    public int delete(int id) throws SQLException {
        int del = 0;
        String sqlD = ("DELETE FROM venta WHERE id=" + id);
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sqlD);
            del = insert.executeUpdate();
        } catch (Exception e) {

        }
        con.close();
        return del;
    }

    public ArrayList<Venta> buscarVentas(
        String fechaDesde, String fechaHasta, Float montoDesde, Float montoHasta
    ) throws SQLException {
        ArrayList<Venta> data = new ArrayList<>();
        AutoDAO autoDao = new AutoDAO();
        ClienteDao clienteDao = new ClienteDao();
        VendedorDao vendedorDao = new VendedorDao();
        String filtroFecha = "";
        String filtroMonto = "";
        String filtros = "";
        if (!fechaDesde.isEmpty() && !fechaHasta.isEmpty()){
            filtroFecha = "(to_date(venta.fecha_venta, 'DD/MM/YYYY') between to_date('"+fechaDesde+"', 'DD/MM/YYYY') and to_date('"+fechaHasta+"', 'DD/MM/YYYY'))";
        };

        if (!montoDesde.isNaN() && !montoHasta.isNaN()){
            filtroMonto = "(venta.monto_total between "+montoDesde+" and "+montoHasta+")";
        };

        if (!filtroFecha.isEmpty() && !filtroMonto.isEmpty()) {
            filtros = "WHERE" + filtroFecha + " OR " + filtroMonto;
        } else if (!filtroFecha.isEmpty() && filtroMonto.isEmpty()) {
            filtros = "WHERE" + filtroFecha;
        } else if (filtroFecha.isEmpty() && !filtroMonto.isEmpty()) {
            filtros =  "WHERE" + filtroMonto;
        } else {
            filtros = "";
        }

        String sql = """
                SELECT
                    venta.id,
                    venta.fecha_venta,
                    venta.auto_id,
                    venta.cliente_id,
                    venta.vendedor_id,
                    venta.monto_total,
                    venta.impuesto,
                    venta.cantidad
                FROM
                    venta
                """ + filtros;
        try {

            con = conectar.getConection();
            insert = con.prepareStatement(sql);
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        con.close();
        return data;
    }
}
