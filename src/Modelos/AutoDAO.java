package Modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AutoDAO {
    Conexion con = new Conexion();
    Connection sqlcon;
    PreparedStatement ps;
    ResultSet rs;

    public List<Auto> listar() {
        List<Auto>autos = new ArrayList<Auto>();
        String tablaAuto = "auto";
        String tablaModelo = "modelo";
        String tablaMarca = "marca";
        String pk = "auto.id";
        String modelo = "CONCAT(marca.nombre, ' ', modelo.nombre, ' ', modelo.anio)";
        String precio = "auto.precio";
        String observacion = "auto.observacion";
        String sql = String.format(
            "SELECT %s AS \"ID\", %s AS \"Modelo\", %s AS \"Precio\", %s AS \"Observacion\" FROM %s INNER JOIN %s ON %s.%s_id = %s.id INNER JOIN %s ON %s.%s_id = %s.id",
            pk, modelo, precio, observacion, tablaAuto, tablaModelo, tablaAuto, tablaModelo, tablaModelo, tablaMarca, tablaModelo, tablaMarca, tablaMarca);
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
        return autos
    }
}
