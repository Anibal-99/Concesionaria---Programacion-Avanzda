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

    public ArrayList<Modelo> getModelo() throws SQLException {
        ArrayList<Modelo> modelos = new ArrayList<>();
        String sql = "select modelo.id as \"ID\", marca.nombre as \"Marca\", modelo.nombre as \"Modelo\", modelo.anio as \"Año\" FROM modelo INNER JOIN marca ON modelo.marca_id = marca.id order by marca.nombre asc, modelo.id desc";
        try {
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(sql);
            rs = ps.executeQuery();
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

    public int agregar(Modelo m) {
        String sql = ("INSERT INTO modelo(nombre,anio, marca_id)values(?,?,?)");
        try {
            MarcaDao marcas = new MarcaDao();
            ArrayList<Marca> listarMarcas = marcas.getMarcas();
            int marca_id = 0;
            for (Marca mar : listarMarcas) {
                if (m.getMarca().equals(mar.getName())) {
                    marca_id = mar.getId();
                }
            }
            System.out.println(marca_id);
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(sql);
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getAnio());
            ps.setInt(3, marca_id);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {

        }
        return 1;
    }

    public ArrayList<Modelo> getListarModelos() throws SQLException {
        ArrayList<Modelo> modelos = new ArrayList<>();
        String sql = "select modelo.id as \"ID\", marca.nombre as \"Marca\", modelo.nombre as \"Modelo\", modelo.anio as \"Año\" FROM modelo INNER JOIN marca ON modelo.marca_id = marca.id order by modelo.id desc";
        try {
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(sql);
            rs = ps.executeQuery();
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

    public int actualizar(Modelo m) {
        int act = 0;
        String sqlU = ("UPDATE modelo SET nombre=?,anio=?,marca_id=? WHERE id=?");
        try {
            MarcaDao marcas = new MarcaDao();
            ArrayList<Marca> listarMarcas = marcas.getMarcas();
            int marca_id = 0;
            for (Marca mar : listarMarcas) {
                if (m.getMarca().equals(mar.getName())) {
                    marca_id = mar.getId();
                }
            }
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(sqlU);
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getAnio());
            ps.setInt(3, marca_id);
            ps.setInt(4, m.getId());
            System.out.println(ps);
            act = ps.executeUpdate();
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
        String sqlD = ("DELETE FROM modelo WHERE id=" + id);
        // System.out.println(sqlD);
        try {
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(sqlD);
            del = ps.executeUpdate();
        } catch (Exception e) {

        }
        return del;
    }
        public ArrayList<Modelo> buscarMarcas(String name) throws SQLException {
        ArrayList<Modelo> modelos = new ArrayList<>();
        String sql = "select modelo.id as \"Id\", marca.nombre as \"Marca\", modelo.nombre as \"Nombre\", modelo.anio as \"Anio\" from modelo inner join marca on modelo.marca_id=marca.id where modelo.nombre="+"'"+name+"'";
        // System.out.println(sql);
        try {
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(sql);
            rs = ps.executeQuery();
            // System.out.println(rs);
            while (rs.next()) {
                Modelo m = new Modelo();
                m.setId(rs.getInt(1));
                m.setMarca(rs.getString(2));
                m.setNombre(rs.getString(3));
                m.setAnio(rs.getInt(4));
                modelos.add(m);
            }
        } catch (Exception e) {
        }
        // System.out.println("");
        return modelos;
    }
}
