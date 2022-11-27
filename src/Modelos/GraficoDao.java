/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Anibal-99
 */
public class GraficoDao {

    PreparedStatement insert;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();

    public int obtenerVentasRegionLocal() throws SQLException {
        int valorVentas = 0;
        String sql = """
                     SELECT
                         SUM(venta.cantidad) as Ventas
                     FROM
                         venta inner join auto on venta.auto_id=auto.id
                         inner join modelo on auto.modelo_id=modelo.id
                         inner join marca on modelo.marca_id=marca.id
                         inner join pais on marca.pais_id=pais.id
                         inner join region on pais.region_id=region.id
                     WHERE
                         region.nombre='Local';
                     """;
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sql);
            rs = insert.executeQuery();

            while (rs.next()) {
                valorVentas = rs.getInt(1);
            }
        } catch (Exception e) {

        }
        return valorVentas;
    }

    public int obtenerVentasRegionSudamerica() throws SQLException {
        int valorVentas = 0;
        String sql = """
                     SELECT
                         SUM(venta.cantidad) as Ventas
                     FROM
                         venta inner join auto on venta.auto_id=auto.id
                         inner join modelo on auto.modelo_id=modelo.id
                         inner join marca on modelo.marca_id=marca.id
                         inner join pais on marca.pais_id=pais.id
                         inner join region on pais.region_id=region.id
                     WHERE
                         region.nombre='Sudamerica';
                     """;
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sql);
            rs = insert.executeQuery();

            while (rs.next()) {
                valorVentas = rs.getInt(1);
            }
        } catch (Exception e) {

        }
        return valorVentas;
    }

    public int obtenerVentasRegionExtranjero() throws SQLException {
        int valorVentas = 0;
        String sql = """
                     SELECT
                         SUM(venta.cantidad) as Ventas
                     FROM
                         venta inner join auto on venta.auto_id=auto.id
                         inner join modelo on auto.modelo_id=modelo.id
                         inner join marca on modelo.marca_id=marca.id
                         inner join pais on marca.pais_id=pais.id
                         inner join region on pais.region_id=region.id
                     WHERE
                         region.nombre='Extrangero';
                     """;
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sql);
            rs = insert.executeQuery();

            while (rs.next()) {
                valorVentas = rs.getInt(1);
            }
        } catch (Exception e) {

        }
        return valorVentas;
    }
}
