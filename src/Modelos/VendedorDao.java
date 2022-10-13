/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author MATIAS
 */
public class VendedorDao {
        
    
    static Conexion con = new Conexion();
    static Connection sqlcon;
    static PreparedStatement ps;
    static ResultSet rs;
    
    
    
    
    
    
    public boolean agregar(Vendedor ven) {
        String sql = ("INSERT INTO vendedor(legajo,telefono,nombre,apellido,localidad,direccion)values(?,?,?,?,?,?)");
        //String auxsql = ("SELECT sq.id FROM (select modelo.id as id, marca.nombre as marca from modelo inner join marca ON modelo.marca_id = marca.id order by marca.nombre asc, modelo.id desc limit ?) as sq order by sq.marca desc, sq.id asc limit 1;");
        try {
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(sql);
            ps.setInt(1, ven.getLegajo());
            ps.setString(2,ven.getTel());
            ps.setString(3,ven.getNombre());
            ps.setString(4,ven.getApellido());
            ps.setString(5,ven.getLocalidad());
            ps.setString(6,ven.getDireccion());
            System.out.println(ven.apellido+ven.tel+ven.legajo+ven.localidad+ven.nombre);
            ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean eliminar(int legajo) throws SQLException {
        String sql = ("DELETE FROM vendedor WHERE legajo=" + legajo);
        try {
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(sql);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean actualizar(Vendedor ven) {
        String sql = ("UPDATE vendedor SET telefono=?,nombre=?,apellido=?,localidad=?,direccion=? WHERE legajo=?");
        try {
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(sql);
            ps.setString(1,ven.getTel());
            ps.setString(2,ven.getNombre());
            ps.setString(3,ven.getApellido());
            ps.setString(4,ven.getLocalidad());
            ps.setString(5,ven.getDireccion());
            ps.setInt(6, ven.getLegajo());
            System.out.println(ps);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    
    public ArrayList<Vendedor> listarVendedores() throws SQLException {
        ArrayList<Vendedor> data = new ArrayList<>();
        String sql = "SELECT vendedor.legajo as \"legajo\", vendedor.nombre as \"Nombre\", vendedor.apellido as \"Apellido\", "
                + "vendedor.telefono as \"Telefono\", vendedor.localidad as \"Localidad\", vendedor.direccion as \"Direccion\" FROM vendedor ORDER BY vendedor.legajo DESC";
        try {
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Vendedor v = new Vendedor();
                v.setLegajo(rs.getInt(1));
                v.setNombre(rs.getString(2));
                v.setApellido(rs.getString(3));
                v.setTel(rs.getString(4));
                v.setLocalidad(rs.getString(5));
                v.setDireccion(rs.getString(6));
                data.add(v);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return data;
    }
    
    
    public Vendedor obtenerVendedor(int legajo) throws SQLException {
        Vendedor v = new Vendedor();
        String sql = "SELECT vendedor.legajo as \"legajo\", vendedor.nombre as \"Nombre\", vendedor.apellido as \"Apellido\", "
                + "vendedor.telefono as \"Telefono\", vendedor.localidad as \"Localidad\", vendedor.direccion as \"Direccion\" FROM vendedor ORDER BY vendedor.legajo DESC";
        try {
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                v.setLegajo(rs.getInt(1));
                v.setNombre(rs.getString(2));
                v.setApellido(rs.getString(3));
                v.setTel(rs.getString(4));
                v.setLocalidad(rs.getString(5));
                v.setDireccion(rs.getString(6));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return v;
    }
}
