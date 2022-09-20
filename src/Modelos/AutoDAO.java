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

    public static List<Auto> listar() {
        List<Auto>autos = new ArrayList<Auto>();
        String tablaAuto = "auto";
        String tablaModelo = "modelo";
        String tablaMarca = "marca";
        String pk = "auto.id";
        String modelo = "CONCAT(marca.nombre, ' ', modelo.nombre, ' ', modelo.anio)";
        String precio = "auto.precio";
        String observacion = "auto.observacion";
        String sql = String.format(
            "SELECT %s AS \"ID\", %s AS \"Modelo\", %s AS \"Precio\", %s AS \"Observacion\" FROM %s INNER JOIN %s ON %s.%s_id = %s.id INNER JOIN %s ON %s.%s_id = %s.id ORDER BY %s.id DESC",
            pk, modelo, precio, observacion, tablaAuto, tablaModelo, tablaAuto, tablaModelo, tablaModelo, tablaMarca, tablaModelo, tablaMarca, tablaMarca, tablaAuto);
        try{
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Auto auto = new Auto(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getFloat(3),
                    rs.getString(4)
                );
                autos.add(auto);
            }
        } catch (Exception e){

        }
        return autos;
    }

    public int agregar(Auto auto, int modelo_id) {
        String sql = ("INSERT INTO auto(observacion,precio,modelo_id)values(?,?,?)");
        String auxsql = ("SELECT sq.id FROM (select modelo.id as id, marca.nombre as marca from modelo inner join marca ON modelo.marca_id = marca.id order by marca.nombre asc, modelo.id desc limit ?) as sq order by sq.marca desc, sq.id asc limit 1;");
        try {
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(auxsql);
            ps.setInt(1, modelo_id);
            rs = ps.executeQuery();
            while (rs.next()){
                modelo_id = rs.getInt(1);
            }
            ps = sqlcon.prepareStatement(sql);
            ps.setString(1, auto.getObservacion());
            ps.setFloat(2, auto.getPrecio());
            ps.setInt(3, modelo_id);
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


}
