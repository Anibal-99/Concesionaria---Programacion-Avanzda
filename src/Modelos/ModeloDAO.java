package Modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ModeloDAO {
    static Conexion con = new Conexion();
    static Connection sqlcon;
    static PreparedStatement ps;
    static ResultSet rs;

    public ArrayList<Modelo> getModelo() throws SQLException{
        ArrayList<Modelo> modelos = new ArrayList<>();
        String sql = "select modelo.id as \"ID\", marca.nombre as \"Marca\", modelo.nombre as \"Modelo\", modelo.anio as \"Año\" FROM modelo INNER JOIN marca ON modelo.marca_id = marca.id order by marca.nombre asc, modelo.id desc";
        try {
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(sql);
            rs = ps.executeQuery();
            // System.out.println(ps);
            while (rs.next()) {
                Modelo modelo = new Modelo();
                modelo.setId(rs.getInt(1));
                modelo.setMarca(rs.getString(2));
                modelo.setNombre(rs.getString(3));
                modelo.setAnio(rs.getInt(4));
                modelos.add(modelo);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return modelos;
    }
}
