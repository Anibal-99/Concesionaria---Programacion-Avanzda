/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;
import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {
    private final String host="127.0.0.1";
    private final String port="5432";
    private final String db="postgres";
    private final String password = "postgres";
    private final String user = "anita";
    
    Connection con;
    public Connection getConection(){
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://"+host+":"+port+"/"+db;
            con = DriverManager.getConnection(url, user, password);

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return con;
    }
}
