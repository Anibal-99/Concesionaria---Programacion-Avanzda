
import Modelos.Vendedor;
import Modelos.VendedorDao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MATIAS
 */
public class TestVendedor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Vendedor v = new Vendedor();
        VendedorDao vd = new VendedorDao();
        v.setNombre("ramonnnn");
        v.setApellido("asdasd");
        v.setLegajo(0);
        v.setLocalidad("cba");
        
        v.setTel("213");
        vd.agregar(v);
        
    }
    
}
