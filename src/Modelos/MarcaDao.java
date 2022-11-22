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
    PaisDao paisDao = new PaisDao();

    public int agregar(Marca m) throws SQLException {
        String sql = ("INSERT INTO marca(nombre,pais_id,observacion)values(?,?,?)");
        int flag = 0;
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sql);
            insert.setString(1, m.getName());
            insert.setInt(2, m.getPais().getId());
            insert.setString(3, m.getObs());
            flag = insert.executeUpdate();
            con.close();
        } catch (Exception e) {}
        return flag == 1 ? 1 : 0;
    }

    public ArrayList<Marca> listarMarcas() throws SQLException {
        ArrayList<Marca> data = new ArrayList<>();
        String sql = "SELECT marca.id, marca.nombre, marca.pais_id, marca.observacion FROM marca ORDER BY marca.id DESC";
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sql);
            rs = insert.executeQuery();
            while (rs.next()) {
                Marca m = new Marca();
                m.setId(rs.getInt(1));
                m.setName(rs.getString(2));
                m.setPais(paisDao.getPaisById(rs.getInt(3)));
                m.setObs(rs.getString(4));
                data.add(m);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        con.close();
        return data;
    }

    public int modificar(Marca mar) throws SQLException {
        int act = 0;
        String sqlU = ("UPDATE marca SET nombre=?,pais_id=?,observacion=? WHERE id=?");
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sqlU);
            insert.setString(1, mar.getName());
            insert.setInt(2, mar.getPais().getId());
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
        con.close();
        return act;
    }

    public int delete(int id) throws SQLException {
        int del = 0;
        String sqlD = ("DELETE FROM marca WHERE id=" + id);
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sqlD);
            del = insert.executeUpdate();
        } catch (Exception e) {

        }
        con.close();
        return del;
    }

    public ArrayList<Marca> filtrarMarcas(String valor) throws SQLException {
        ArrayList<Marca> marcas = new ArrayList<>();
        String sql = "SELECT marca.id, marca.nombre, marca.pais_id, marca.observacion FROM marca INNER JOIN pais ON marca.pais_id = pais.id WHERE marca.nombre ~ ? OR pais.nombre ~ ?";
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sql);
            insert.setString(1, valor);
            insert.setString(2, valor);
            rs = insert.executeQuery();
            while (rs.next()) {
                Marca m = new Marca();
                m.setId(rs.getInt(1));
                m.setName(rs.getString(2));
                m.setPais(paisDao.getPaisById(rs.getInt(3)));
                m.setObs(rs.getString(4));
                marcas.add(m);
            }
        } catch (Exception e) {
        }
        con.close();
        return marcas;
    }

    public ArrayList<Marca> getMarcas() throws SQLException {
        ArrayList<Marca> marcas = new ArrayList<>();
        String sql = "select marca.id as \"ID\", marca.nombre as \"Nombre\", marca.pais_id as \"Pais\", marca.observacion as \"Observacion\" from marca order by marca.id desc";
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sql);
            rs = insert.executeQuery();
            while (rs.next()) {
                Marca m = new Marca();
                m.setId(rs.getInt(1));
                m.setName(rs.getString(2));
                m.setPais(paisDao.getPaisById(rs.getInt(3)));
                m.setObs(rs.getString(4));
                marcas.add(m);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        con.close();
        return marcas;
    }

    public Marca getMarcaById(int id) throws SQLException {
        Marca marca = new Marca();
        String sql = "select * from marca where id = ?";
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sql);
            insert.setInt(1, id);
            rs = insert.executeQuery();
            while (rs.next()) {
                marca.setId(rs.getInt(1));
                marca.setName(rs.getString(2));
                marca.setObs(rs.getString(3));
                marca.setPais(paisDao.getPaisById(rs.getInt(4)));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        con.close();
        return marca;
    }
}
