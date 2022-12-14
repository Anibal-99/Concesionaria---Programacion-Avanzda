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
public class ColorDao {

    PreparedStatement insert;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();

    Color color = new Color();

    public ArrayList<Color> getColor() throws SQLException {
        ArrayList<Color> colores = new ArrayList<>();
        String sql = "select*from color order by nombre asc";
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sql);
            rs = insert.executeQuery();
            while (rs.next()) {
                Color c = new Color();
                c.setId(rs.getInt(1));
                c.setNombre(rs.getString(2));
                colores.add(c);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        con.close();
        return colores;
    }

    public Color getColorById(int id) throws SQLException {
        Color c = new Color();
        String sql = "select color.id, color.nombre from color where id = ?";
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sql);
            insert.setInt(1, id);
            rs = insert.executeQuery();

            while (rs.next()) {
                c.setId(rs.getInt(1));
                c.setNombre(rs.getString(2));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        con.close();
        return c;
    }
}
