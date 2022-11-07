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
public class VendedorDao {

    PreparedStatement insert;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    Vendedor vendedor = new Vendedor();
    PaisDao paisDao = new PaisDao();

    public int agregar(Vendedor vendedor) throws SQLException {
        String sql = ("INSERT INTO vendedor(nombre,pais_id,apellido,dni)values(?,?,?,?)");
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sql);
            insert.setString(1, vendedor.getNombre());

            insert.setInt(2, vendedor.getPais().getId());
            insert.setString(3, vendedor.getApellido());
            insert.setString(4, vendedor.getDni());
            insert.executeUpdate();
        } catch (Exception e) {

        }
        con.close();
        return 1;
    }

    public ArrayList<Vendedor> listarVendedor() throws SQLException {
        ArrayList<Vendedor> data = new ArrayList<>();
        String sql = "select vendedor.id, vendedor.nombre, vendedor.apellido, vendedor.dni, vendedor.pais_id from vendedor order by vendedor.id desc";
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sql);
            rs = insert.executeQuery();
            while (rs.next()) {
                Vendedor v = new Vendedor();
                v.setId(rs.getInt(1));
                v.setNombre(rs.getString(2));
                v.setApellido(rs.getString(3));
                v.setDni(rs.getString(4));
                v.setPais(paisDao.getPaisById(rs.getInt(5)));
                data.add(v);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        con.close();
        return data;
    }

    public int actualizar(Vendedor vendedor) throws SQLException {
        int act = 0;
        String sqlU = ("UPDATE vendedor SET nombre=?,apellido=?,dni=?,pais_id=? WHERE id=?");
        System.out.println("llega");
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sqlU);
            insert.setString(1, vendedor.getNombre());
            insert.setString(2, vendedor.getApellido());
            insert.setString(3, vendedor.getDni());
            insert.setInt(4, vendedor.getPais().getId());
            insert.setInt(5, vendedor.getId());
            act = insert.executeUpdate();
            if (act == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException e) {

        }
        con.close();
        return act;
    }

    public int delete(int id) throws SQLException {
        int del = 0;
        String sqlD = ("DELETE FROM vendedor WHERE id=" + id);
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sqlD);
            del = insert.executeUpdate();
        } catch (Exception e) {

        }
        con.close();
        return del;
    }

    public ArrayList<Vendedor> buscarVendedores(String name) throws SQLException {
        ArrayList<Vendedor> vendedores = new ArrayList<>();
        String sql = "select vendedor.id, vendedor.nombre, vendedor.apellido, vendedor.dni, vendedor.pais_id from vendedor where vendedor.nombre = " + "'" + name + "'" + "order by vendedor.id desc";
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sql);
            rs = insert.executeQuery();
            while (rs.next()) {
                Vendedor v = new Vendedor();
                v.setId(rs.getInt(1));
                v.setNombre(rs.getString(2));
                v.setApellido(rs.getString(3));
                v.setDni(rs.getString(4));
                v.setPais(paisDao.getPaisById(rs.getInt(5)));
                vendedores.add(v);
            }
        } catch (Exception e) {
        }
        con.close();
        return vendedores;
    }

    public ArrayList<Vendedor> getVendedores() throws SQLException {
        ArrayList<Vendedor> vendedores = new ArrayList<>();
        String sql = "select vendedor.id, vendedor.nombre, vendedor.apellido, vendedor.dni, vendedor.pais_id from vendedor order by vendedor.id asc";
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sql);
            rs = insert.executeQuery();

            while (rs.next()) {
                Vendedor v = new Vendedor();
                v.setId(rs.getInt(1));
                v.setNombre(rs.getString(2));
                v.setApellido(rs.getString(3));
                v.setDni(rs.getString(4));
                v.setPais(paisDao.getPaisById(rs.getInt(5)));

                vendedores.add(v);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        con.close();
        return vendedores;
    }

    public Vendedor getVendedorById(int id) throws SQLException {
        Vendedor vendedor = new Vendedor();
        String sql = " select vendedor.id, vendedor.nombre, vendedor.apellido, vendedor.dni, vendedor.pais_id from vendedor where vendedor.id = ? order by vendedor.id desc";
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sql);
            insert.setInt(1, id);
            rs = insert.executeQuery();
            while (rs.next()) {
                vendedor.setId(rs.getInt(1));
                vendedor.setNombre(rs.getString(2));
                vendedor.setApellido(rs.getString(3));
                vendedor.setDni(rs.getString(4));
                vendedor.setPais(paisDao.getPaisById(rs.getInt(5)));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        con.close();
        return vendedor;
    }
}
