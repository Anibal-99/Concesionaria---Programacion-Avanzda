/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.awt.List;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Anibal-99
 */
public class MarcaDao {

    PreparedStatement insert;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    Marca m = new Marca();

    //public void agregar(String name, String pais, String obs) throws SQLException{
    //    con = conectar.getConection();
    //    insert = con.prepareStatement("INSERT INTO marca(nombre,pais,observacion)values(?,?,?)");
    //    insert.setString(1, name);
    //    insert.setString(2, pais);
    //    insert.setString(3, obs);
    //    insert.executeUpdate();
    //}
    public int agregar(Marca m) {
        String sql = ("INSERT INTO marca(nombre,pais_id,observacion)values(?,?,?)");
        try {
            PaisDao paises = new PaisDao();
            ArrayList<Pais> listarPaises = paises.getPais();
            int pais_id = 0;
            for (Pais p : listarPaises) {
                if (m.getPais().equals(p.getName())) {
                    pais_id = p.getId();
                }
            }
            con = conectar.getConection();
            insert = con.prepareStatement(sql);
            insert.setString(1, m.getName());
            insert.setInt(2, pais_id);
            insert.setString(3, m.getObs());
            System.out.println(insert);
            insert.executeUpdate();
        } catch (Exception e) {

        }
        return 1;
    }

    public ArrayList<Marca> listarMarcas() throws SQLException {
        ArrayList<Marca> data = new ArrayList<>();
        String sql = "SELECT marca.id as \"ID\", marca.nombre as \"Nombre\", pais.nombre as \"Pais\", marca.observacion as \"Observacion\" FROM marca INNER JOIN pais ON marca.pais_id = pais.id ORDER BY marca.id DESC";
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sql);
            rs = insert.executeQuery();
            while (rs.next()) {
                Marca m = new Marca();
                m.setId(rs.getInt(1));
                m.setName(rs.getString(2));
                m.setPais(rs.getString(3));
                m.setObs(rs.getString(4));
                data.add(m);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return data;
    }

    public int modificar(Marca mar) {
        int act = 0;
        String sqlU = ("UPDATE marca SET nombre=?,pais_id=?,observacion=? WHERE id=?");
        try {
            PaisDao paises = new PaisDao();
            ArrayList<Pais> listarPaises = paises.getPais();
            int pais_id = 0;
            for (Pais p : listarPaises) {
                if (mar.getPais().equals(p.getName())) {
                    pais_id = p.getId();
                }
            }
            con = conectar.getConection();
            insert = con.prepareStatement(sqlU);
            insert.setString(1, mar.getName());
            insert.setInt(
                2,
                pais_id
            );
            insert.setString(3, mar.getObs());
            insert.setInt(4, mar.getId());
            act = insert.executeUpdate();
            if (act == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {

        }
        return act;
    }

    public int delete(int id) throws SQLException {
        int del = 0;
        String sqlD = ("DELETE FROM marca WHERE id=" + id);
        System.out.println(sqlD);
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sqlD);
            del = insert.executeUpdate();
        } catch (Exception e) {

        }
        return del;
    }

    public ArrayList<Marca> filtrarMarcas(String name) throws SQLException {
        ArrayList<Marca> marcas = new ArrayList<>();
        String sql = "select * from marca where marca.nombre="+"'"+name+"'";
        System.out.println(sql);
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sql);
            rs=insert.executeQuery();
            System.out.println(rs);
            while(rs.next()){
                Marca m = new Marca();
                m.setId(rs.getInt(1));
                m.setName(rs.getString(2));
                m.setPais(rs.getString(3));
                m.setObs(rs.getString(4));
                marcas.add(m);
            }
        } catch (Exception e) {
        }
        System.out.println("");
        return marcas;
    }
}
