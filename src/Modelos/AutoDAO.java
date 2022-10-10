package Modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutoDAO {

    static Conexion con = new Conexion();
    static Connection sqlcon;
    static PreparedStatement ps;
    static ResultSet rs;
    ColorDao colorDao = new ColorDao();

    public ArrayList<Auto> listar()throws SQLException {
        ArrayList<Auto> autos = new ArrayList<Auto>();
        String tablaAuto = "auto";
        String tablaModelo = "modelo";
        String tablaMarca = "marca";
        String pk = "auto.id";
        String modelo = "CONCAT(marca.nombre, ' ', modelo.nombre, ' ', modelo.anio)";
        String precio = "auto.precio";
        String observacion = "auto.observacion";
        String sql = String.format(
                "SELECT %s AS \"ID\", %s AS \"Modelo\", %s AS \"Precio\", %s AS \"Observacion\", auto.color_id FROM %s INNER JOIN %s ON %s.%s_id = %s.id INNER JOIN %s ON %s.%s_id = %s.id ORDER BY %s.id DESC",
                pk, modelo, precio, observacion, tablaAuto, tablaModelo, tablaAuto, tablaModelo, tablaModelo, tablaMarca, tablaModelo, tablaMarca, tablaMarca, tablaAuto);
        try {
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Auto auto = new Auto();
                auto.setId(rs.getInt(1));
                auto.setModelo(rs.getString(2));
                auto.setPrecio(rs.getFloat(3));
                auto.setObservacion(rs.getString(4));
                System.out.println(colorDao.getColorById(rs.getInt(5)));
                auto.setColor(colorDao.getColorById(rs.getInt(5)));
                autos.add(auto);
            }
        } catch (Exception e) {

        }
        return autos;
    }

    public int agregar(Auto auto, int modelo_id) {
        String sql = ("INSERT INTO auto(observacion,precio,modelo_id, color_id)values(?,?,?,?)");
        String auxsql = ("SELECT sq.id FROM (select modelo.id as id, marca.nombre as marca from modelo inner join marca ON modelo.marca_id = marca.id order by marca.nombre asc, modelo.id desc limit ?) as sq order by sq.marca desc, sq.id asc limit 1;");
        try {
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(auxsql);
            ps.setInt(1, modelo_id);
            rs = ps.executeQuery();

            while (rs.next()) {
                modelo_id = rs.getInt(1);
            }
            
            ps = sqlcon.prepareStatement(sql);
            ps.setString(1, auto.getObservacion());
            ps.setFloat(2, auto.getPrecio());
            ps.setInt(3, modelo_id);
            ps.setInt(4, auto.getColor().getId());
            System.out.println(ps);
            ps.executeUpdate();

        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    public int eliminar(int id) throws SQLException {
        String sql = ("DELETE FROM auto WHERE id=" + id);
        try {
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    public int actualizar(Auto auto, int modelo_id) {
        int flag = 0;
        String auxsql = ("SELECT sq.id FROM (select modelo.id as id, marca.nombre as marca from modelo inner join marca ON modelo.marca_id = marca.id order by marca.nombre asc, modelo.id desc limit ?) as sq order by sq.marca desc, sq.id asc limit 1;");
        try {
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(auxsql);
            ps.setInt(1, modelo_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                modelo_id = rs.getInt(1);
            }
            String sql = String.format("UPDATE auto SET observacion = '%s', precio = %s, modelo_id = %s, color_id = %s WHERE auto.id = %s",
                    auto.observacion, auto.precio, modelo_id, auto.getColor().getId(), auto.id);
            ps = sqlcon.prepareStatement(sql);
            flag = ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
        return flag;
    }

    public ArrayList<Auto> buscarAutos(String name) throws SQLException {
        ArrayList<Auto> autos = new ArrayList<>();
        String tablaAuto = "auto";
        String tablaModelo = "modelo";
        String tablaMarca = "marca";
        String pk = "auto.id";
        String modelo = "CONCAT(marca.nombre, ' ', modelo.nombre, ' ', modelo.anio)";
        String precio = "auto.precio";
        String tablaColor = "color";
        String color = "auto.color";
        String observacion = "auto.observacion";
        String sql = String.format(
                "SELECT %s AS \"ID\", %s AS \"Modelo\", %s AS \"Precio\", %s AS \"Observacion\", auto.color_id FROM %s INNER JOIN %s ON %s.%s_id = %s.id INNER JOIN %s ON %s.%s_id = %s.id WHERE marca.nombre=" + "'" + name + "'" + " OR modelo.nombre =" + "'" + name + "'",
                pk, modelo, precio, observacion, tablaAuto, tablaModelo, tablaAuto, tablaModelo, tablaModelo, tablaMarca, tablaModelo, tablaMarca, tablaMarca);
        try {
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(sql);
            rs = ps.executeQuery();
            System.out.println(ps);
            while (rs.next()) {
                Auto auto = new Auto();
                auto.setId(rs.getInt(1));
                auto.setModelo(rs.getString(2));
                auto.setPrecio(rs.getFloat(3));
                auto.setObservacion(rs.getString(4));
                auto.setColor(colorDao.getColorById(rs.getInt(5)));
                autos.add(auto);
            }
        } catch (Exception e) {
        }
        // System.out.println("");
        return autos;
    }
}
