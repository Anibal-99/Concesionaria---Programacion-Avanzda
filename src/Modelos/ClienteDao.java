/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.*;

/**
 *
 * @author Anibal-99
 */
public class ClienteDao {

    PreparedStatement insert;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    Cliente c = new Cliente();
    PaisDao paisDao = new PaisDao();

    public int agregar(Cliente c) {
        String sql = ("INSERT INTO cliente(nombre,pais_id,apellido,razon_social,cuit,telefono,direccion,localidad)values(?,?,?,?,?,?,?,?)");
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sql);
            insert.setString(1, c.getNombre());
            insert.setInt(2, c.getPais().getId());
            insert.setString(3, c.getApellido());
            insert.setString(4, c.getRazonSocial());
            insert.setString(5, c.getCuit());
            insert.setString(6, c.getTel());
            insert.setString(7, c.getDireccion());
            insert.setString(8, c.getLocalidad());
            insert.executeUpdate();
        } catch (Exception e) {

        }
        return 1;
    }

    public int modificar(Cliente c) {
        int act = 0;
        String sqlU = ("UPDATE cliente SET nombre=?,apellido=?,razon_social=?,cuit=?,telefono=?,direccion=?,localidad=?,pais_id=? WHERE id=?");

        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sqlU);
            insert.setString(1, c.getNombre());
            insert.setString(2, c.getApellido());
            insert.setString(3, c.getRazonSocial());
            insert.setString(4, c.getCuit());
            insert.setString(5, c.getTel());
            insert.setString(6, c.getDireccion());
            insert.setString(7, c.getLocalidad());
            insert.setInt(8, c.getPais().getId());
            insert.setInt(9, c.getId());
            act = insert.executeUpdate();

            if (act == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException e) {

        }
        return act;
    }

    public ArrayList<Cliente> listarClientes() throws SQLException {
        ArrayList<Cliente> data = new ArrayList<>();
        String sql = "select cliente.id, cliente.nombre, cliente.apellido, cliente.razon_social, cliente.cuit, cliente.telefono, cliente.direccion, cliente.localidad, cliente.pais_id from cliente order by cliente.id desc";
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sql);
            rs = insert.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt(1));
                c.setNombre(rs.getString(2));
                c.setApellido(rs.getString(3));
                c.setRazonSocial(rs.getString(4));
                c.setCuit(rs.getString(5));
                c.setTel(rs.getString(6));
                c.setDireccion(rs.getString(7));
                c.setLocalidad(rs.getString(8));
                c.setPais(paisDao.getPaisById(rs.getInt(9)));
                data.add(c);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return data;
    }

    public int delete(int id) throws SQLException {
        int del = 0;
        String sqlD = ("DELETE FROM cliente WHERE id=" + id);
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sqlD);
            del = insert.executeUpdate();
        } catch (Exception e) {

        }
        return del;
    }

    public ArrayList<Cliente> buscarClientes(String name) throws SQLException {
        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "select cliente.id, cliente.nombre, cliente.apellido, cliente.razon_social, cliente.cuit, cliente.telefono, cliente.direccion, cliente.localidad, cliente.pais_id from cliente where cliente.nombre = "+ "'"+  name  +"'" + "order by cliente.id desc";
        try {
            con = conectar.getConection();
            insert = con.prepareStatement(sql);
            rs = insert.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt(1));
                c.setNombre(rs.getString(2));
                c.setApellido(rs.getString(3));
                c.setRazonSocial(rs.getString(4));
                c.setCuit(rs.getString(5));
                c.setTel(rs.getString(6));
                c.setDireccion(rs.getString(7));
                c.setLocalidad(rs.getString(8));
                c.setPais(paisDao.getPaisById(rs.getInt(9)));
                clientes.add(c);
            }
        } catch (Exception e) {
        }
        return clientes;
    }
}
