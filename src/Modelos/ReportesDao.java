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

    public ArrayList<modelReporteRow> generarReporteAuto(String valorOrden, int valorAnio) throws SQLException {
        ArrayList<modelReporteRow> data = new ArrayList<>();
        String sql = """
                    SELECT
                         CONCAT(marca.nombre, ' ', modelo.nombre) as "MODELO",
                         SUM(auto.costo * venta.cantidad) as "COSTO TOTAL",
                         SUM(venta.monto_total) as "PRECIO TOTAL",
                         SUM(venta.monto_total - (auto.costo * venta.cantidad)) as ganancia,
                         SUM(venta.cantidad) as ventas
                     FROM
                         auto INNER JOIN modelo ON auto.modelo_id = modelo.id
                         INNER JOIN marca ON modelo.marca_id = marca.id
                         INNER JOIN venta ON venta.auto_id = auto.id
                     WHERE
                         extract(year from to_date(venta.fecha_venta, 'DD/MM/YYYY'))=?
                     GROUP BY
                         modelo.nombre,
                         marca.nombre
                     ORDER BY
                         """
                + valorOrden + """
                      DESC
                     ;
                     """;

        con = conectar.getConection();
        insert = con.prepareStatement(sql);
        insert.setInt(1, valorAnio);
        //insert.setString(2, valorOrden);
        System.out.println(insert);
        rs = insert.executeQuery();

        while (rs.next()) {
            modelReporteRow row = new modelReporteRow();
            row.setModelo(rs.getString(1));
            row.setCosto(rs.getFloat(2));

            row.setPrecio(rs.getFloat(3));
            row.setGanancia(rs.getFloat(4));
            row.setVentas(rs.getInt(5));
            data.add(row);
        }
        con.close();
        return data;
    }

    public ArrayList<vendedorReporteRow> generarReporteVendedores(String valorOrden, int valorAnio) throws SQLException {
        ArrayList<vendedorReporteRow> data = new ArrayList<>();
        String sql = """
                    SELECT
                        CONCAT(vendedor.nombre, ' ', vendedor.apellido) as "VENDEDOR",
                        SUM(venta.monto_total - (auto.costo * venta.cantidad)) as ganancia,
                        SUM(venta.cantidad) as ventas
                    FROM
                        auto INNER JOIN modelo ON auto.modelo_id = modelo.id
                        INNER JOIN marca ON modelo.marca_id = marca.id
                        INNER JOIN venta ON venta.auto_id = auto.id
                        INNER JOIN vendedor ON venta.vendedor_id = vendedor.id
                    WHERE
                        extract(year from to_date(venta.fecha_venta, 'DD/MM/YYYY'))=?
                    GROUP BY
                        vendedor.nombre,
                        vendedor.apellido
                    ORDER BY
                        """
                + valorOrden + """
                     DESC
                    ;
                    """;

        con = conectar.getConection();
        insert = con.prepareStatement(sql);
        insert.setInt(1, valorAnio);
        System.out.println(insert);
        rs = insert.executeQuery();

        while (rs.next()) {
            vendedorReporteRow row = new vendedorReporteRow();
            row.setVendedor(rs.getString(1));
            row.setGanancia(rs.getFloat(2));
            row.setVentas(rs.getInt(3));
            data.add(row);
        }
        con.close();
        return data;
    }
}
