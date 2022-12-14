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
    MarcaDao marcaDao = new MarcaDao();

    public ArrayList<Modelo> getModelos() throws SQLException {
        ArrayList<Modelo> modelos = new ArrayList<>();
        String sql = "select modelo.id, modelo.marca_id, modelo.nombre, modelo.anio from modelo order by modelo.id desc";
        try {
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Modelo modelo = new Modelo();
                modelo.setId(rs.getInt(1));
                modelo.setMarca(marcaDao.getMarcaById(rs.getInt(2)));
                modelo.setNombre(rs.getString(3));
                modelo.setAnio(rs.getInt(4));
                modelos.add(modelo);
            }
            sqlcon.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return modelos;

    }

    public int agregar(Modelo m) throws SQLException {
        String sql = ("INSERT INTO modelo(nombre,anio, marca_id)values(?,?,?)");
        try {
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(sql);
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getAnio());
            ps.setInt(3, m.getMarca().getId());
            ps.executeUpdate();
            sqlcon.close();
        } catch (Exception e) {}
        return 1;
    }

    public ArrayList<Modelo> getListarModelos() throws SQLException {
        ArrayList<Modelo> modelos = new ArrayList<>();
        String sql = "select modelo.id, modelo.marca_id, modelo.nombre, modelo.anio FROM modelo order by modelo.id desc";
        try {
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Modelo modelo = new Modelo();
                modelo.setId(rs.getInt(1));
                modelo.setMarca(marcaDao.getMarcaById(rs.getInt(2)));
                modelo.setNombre(rs.getString(3));
                modelo.setAnio(rs.getInt(4));
                modelos.add(modelo);
            }
            sqlcon.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return modelos;
    }

    public int actualizar(Modelo m) {
        int act = 0;
        String sqlU = ("UPDATE modelo SET nombre=?,anio=?,marca_id=? WHERE id=?");
        try {
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(sqlU);
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getAnio());
            ps.setInt(3, m.getMarca().getId());
            ps.setInt(4, m.getId());
            act = ps.executeUpdate();
            sqlcon.close();
            if (act == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {}
        return act;
    }

    public int delete(int id) throws SQLException {
        int del = 0;
        String sqlD = ("DELETE FROM modelo WHERE id=" + id);
        try {
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(sqlD);
            del = ps.executeUpdate();
            sqlcon.close();
        } catch (Exception e) {

        }
        return del;
    }

    public ArrayList<Modelo> buscarModelos(String name) throws SQLException {
        ArrayList<Modelo> modelos = new ArrayList<>();
        String sql = "select modelo.id, modelo.nombre, modelo.anio, modelo.marca_id from modelo inner join marca on modelo.marca_id = marca.id where modelo.nombre ~ ? or marca.nombre ~ ?";
        try {
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                Modelo m = new Modelo();
                m.setId(rs.getInt(1));
                m.setNombre(rs.getString(2));
                m.setAnio(rs.getInt(3));
                m.setMarca(marcaDao.getMarcaById(rs.getInt(4)));
                modelos.add(m);
            }
            sqlcon.close();
        } catch (Exception e) {
        }
        return modelos;
    }

    public Modelo getModeloById(int id) throws SQLException {
        Modelo modelos = new Modelo();
        MarcaDao marcaDao = new MarcaDao();
        String sql = "select * from modelo where id = ?";
        try {
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                modelos.setId(rs.getInt(1));
                modelos.setNombre(rs.getString(2));
                modelos.setAnio(rs.getInt(3));
                modelos.setMarca(marcaDao.getMarcaById(rs.getInt(4)));
            }
            sqlcon.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return modelos;
    }
}
