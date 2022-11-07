package Modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class AutoDAO {

    static Conexion con = new Conexion();
    static Connection sqlcon;
    static PreparedStatement ps;
    static ResultSet rs;
    ColorDao colorDao = new ColorDao();
    ModeloDAO modeloDao = new ModeloDAO();

    public ArrayList<Auto> listar() throws SQLException {
        ArrayList<Auto> autos = new ArrayList<Auto>();
        String sql = "SELECT id, precio, costo, observacion, color_id, modelo_id FROM auto";
        try {
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Auto auto = new Auto();
                auto.setId(rs.getInt(1));
                auto.setPrecio(rs.getFloat(2));
                auto.setCosto(rs.getFloat(3));
                auto.setObservacion(rs.getString(4));
                auto.setColor(colorDao.getColorById(rs.getInt(5)));
                auto.setModelo(modeloDao.getModeloById(rs.getInt(6)));
                autos.add(auto);
            }
            con.close();
        } catch (Exception e) {

        }
        return autos;
    }

    public int agregar(Auto auto) throws SQLException {
        System.out.println(auto.getModelo().getId());
        String sql = ("INSERT INTO auto(observacion,precio,costo,modelo_id, color_id)values(?,?,?,?,?)");
        try {
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(sql);
            ps.setString(1, auto.getObservacion());
            ps.setFloat(2, auto.getPrecio());
            ps.setFloat(3, auto.getCosto());
            ps.setInt(4, auto.getModelo().getId());
            ps.setInt(5, auto.getColor().getId());
            System.out.println(ps);
            ps.executeUpdate();

        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    public int eliminar(int id) throws SQLException {
        String sql = ("DELETE FROM auto WHERE id=" + id);
        try {
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    public int actualizar(Auto auto) throws SQLException {
        int flag = 0;
        try {
            sqlcon = con.getConection();
            String sql = String.format("UPDATE auto SET observacion = '%s', precio = %s, costo=%s, modelo_id = %s, color_id = %s WHERE auto.id = %s",
                    auto.observacion, auto.precio, auto.costo, auto.getModelo().getId(), auto.getColor().getId(), auto.id);
            ps = sqlcon.prepareStatement(sql);
            flag = ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
        return flag;
    }

    public ArrayList<Auto> buscarAutos(String name) throws SQLException {
        ArrayList<Auto> autos = new ArrayList<>();
        String sql = "SELECT id, precio, observacion, color_id, modelo_id FROM auto";
        try {
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(sql);
            rs = ps.executeQuery();
            System.out.println(ps);
            while (rs.next()) {
                Auto auto = new Auto();
                auto.setId(rs.getInt(1));
                auto.setPrecio(rs.getFloat(2));
                auto.setObservacion(rs.getString(3));
                auto.setColor(colorDao.getColorById(rs.getInt(4)));
                auto.setModelo(modeloDao.getModeloById(rs.getInt(5)));
                autos.add(auto);
            }
        } catch (Exception e) {
        }
        return autos;
    }

    public ArrayList<Auto> getAutos() throws SQLException {
        ArrayList<Auto> autos = new ArrayList<>();
        String sql = "SELECT id, precio, observacion, color_id, modelo_id FROM auto";
        try {
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Auto auto = new Auto();
                auto.setId(rs.getInt(1));
                auto.setPrecio(rs.getFloat(2));
                auto.setObservacion(rs.getString(3));
                auto.setColor(colorDao.getColorById(rs.getInt(4)));
                auto.setModelo(modeloDao.getModeloById(rs.getInt(5)));
                autos.add(auto);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return autos;
    }

    public Auto getAutoById(int id) throws SQLException {
        Auto auto = new Auto();
        String sql = " SELECT id, precio, observacion, color_id, modelo_id FROM auto where auto.id =? order by auto.id desc";
        try {
            sqlcon = con.getConection();
            ps = sqlcon.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                auto.setId(rs.getInt(1));
                auto.setPrecio(rs.getFloat(2));
                auto.setObservacion(rs.getString(3));
                auto.setColor(colorDao.getColorById(rs.getInt(4)));
                auto.setModelo(modeloDao.getModeloById(rs.getInt(5)));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return auto;
    }
}
