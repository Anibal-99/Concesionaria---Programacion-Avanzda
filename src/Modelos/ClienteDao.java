/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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

    public int agregar(Cliente c) {
        String sql = ("INSERT INTO cliente(nombre,pais_id,apellido,razon_social,cuit,telefono,direccion,localidad)values(?,?,?,?,?,?,?,?)");
        try {
            PaisDao paises = new PaisDao();
            ArrayList<Pais> listarPaises = paises.getPais();
            int pais_id = 0;
            for (Pais p : listarPaises) {
                if (c.getPais().equals(p.getName())) {
                    pais_id = p.getId();
                }
            }
            con = conectar.getConection();
            insert = con.prepareStatement(sql);
            insert.setString(1, c.getNombre());
            insert.setInt(2, pais_id);
            insert.setString(3, c.getApellido());
            insert.setString(4, c.getRazonSocial());
            insert.setString(5, c.getCuit());
            insert.setString(6, c.getTel());
            insert.setString(7, c.getDireccion());
            insert.setString(8, c.getLocalidad());
            System.out.println(insert);
            insert.executeUpdate();
        } catch (Exception e) {

        }
        return 1;
    }
}
