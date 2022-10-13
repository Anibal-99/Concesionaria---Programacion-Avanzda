/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import Modelos.VendedorDao;
import Modelos.Vendedor;
/**
 *
 * @author MATIAS
 */
public class GestorVendedor {
    private VendedorDao dao = new VendedorDao();
    
    
    public DefaultTableModel listarVendedores() throws SQLException{
        try{
            String[] encabezado = {"Legajo","Nombre","Apellido","Telefono","Localidad","Direccion"};
            DefaultTableModel tm = new DefaultTableModel(null,encabezado);
            for(Vendedor v : dao.listarVendedores()){
                String[] row = new String[6];
                row[0]=v.getLegajo()+"";
                row[1]=v.getNombre();
                row[2]=v.getApellido();
                row[3]=v.getTel();
                row[4]=v.getLocalidad();
                row[5]=v.getDireccion();
                tm.addRow(row);
            }
            return tm;
        }catch(Exception e){
            //mensaje de error
            return null;
        }
    }

    public boolean modificarVendedor(String legajo, String nombre, String apellido, String tel, String localidad, String direccion) {
        if(this.validarCampos(legajo, nombre, apellido, tel, localidad)){
            try{
                Vendedor v = dao.obtenerVendedor(Integer.parseInt(legajo));
                v.setNombre(nombre);
                v.setApellido(apellido);
                v.setTel(tel);
                v.setLocalidad(localidad);
                v.setDireccion(direccion);
                this.dao.actualizar(v);
                return true;
            }catch(Exception e){
                return false;
            }
        }
        return false;
    }
    
    
     public boolean agregarVendedor(String legajo, String nombre, String apellido, String tel, String localidad, String direccion) {
        if(this.validarCampos(legajo, nombre, apellido, tel, localidad)){
            try{
                Vendedor v = new Vendedor();
                v.setLegajo(Integer.parseInt(legajo));
                v.setNombre(nombre);
                v.setApellido(apellido);
                v.setTel(tel);
                v.setLocalidad(localidad);
                v.setDireccion(direccion);
                this.dao.agregar(v);
                return true;
            }catch(Exception e){
                return false;
            }
        }
        return false;
    }
    
    public boolean eliminarVendedor(String legajo) throws SQLException{
        return dao.eliminar(Integer.parseInt(legajo));
    }
     
     

    private boolean validarCampos(String legajo, String nombre, String apellido, String tel, String localidad) {
        String telAux = tel.trim().replace("+","").replace("-","");
        return isNumeric(legajo)&&isNumeric(telAux)&&!nombre.equals("")&&!apellido.equals("")&&!localidad.equals("");
    }
    
    
    private boolean isNumeric(String num){
        try{
            int a = Integer.parseInt(num);
            return true;
        }catch(Exception e){
            return false;
        }   
    }
}
