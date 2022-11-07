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
public class RegionDao {

    PreparedStatement insert;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    Region region = new Region();

    public ArrayList<Region> getRegion() throws SQLException {
        ArrayList<Region> regiones = new ArrayList<>();
        String sql = "select region.id, region.nombre from region";
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sql);
            rs = insert.executeQuery();
            while (rs.next()) {
                Region r = new Region();
                r.setId(rs.getInt(1));
                r.setNombre(rs.getString(2));
                regiones.add(r);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        con.close();
        return regiones;
    }

    public Region getRegionById(int id) throws SQLException {
        Region region = new Region();
        String sql = "select region.id, region.nombre from region where id = ?";
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sql);
            insert.setInt(1, id);
            rs = insert.executeQuery();
            while (rs.next()) {
                region.setId(rs.getInt(1));
                region.setNombre(rs.getString(2));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        con.close();
        return region;
    }
}
