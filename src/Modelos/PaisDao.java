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

<<<<<<< HEAD

=======
>>>>>>> 9005e7900af497384ba52e1d00c88a2aa2090756
/**
 *
 * @author Anibal-99
 */
public class PaisDao {

    PreparedStatement insert;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    Pais p = new Pais();

    public ArrayList<Pais> getPais() throws SQLException{
        ArrayList<Pais> paises = new ArrayList<>();
<<<<<<< HEAD
        String sql = "select*from pais order by nombre asc";
=======
        String sql = "select nombre from pais";
>>>>>>> 9005e7900af497384ba52e1d00c88a2aa2090756
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sql);
            rs = insert.executeQuery();
            while (rs.next()) {
                Pais p = new Pais();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setCodigo(rs.getString(3));
                paises.add(p);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return paises;
    }
}
