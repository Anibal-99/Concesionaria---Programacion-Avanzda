/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Anibal-99
 */
public class AutoDao {

    PreparedStatement insert;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
}
