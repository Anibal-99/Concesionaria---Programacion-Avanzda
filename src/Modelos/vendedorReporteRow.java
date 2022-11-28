package Modelos;

/**
 *
 * @author Anibal-99
 */
public class vendedorReporteRow {
    String vendedor;
    float ganancia;
    int ventas;

    public vendedorReporteRow(String vendedor, float ganancia, int ventas) {
        this.vendedor = vendedor;
        this.ganancia = ganancia;
        this.ventas = ventas;
    }

    public vendedorReporteRow() {
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public float getGanancia() {
        return ganancia;
    }

    public void setGanancia(float ganancia) {
        this.ganancia = ganancia;
    }

    public int getVentas() {
        return ventas;
    }

    public void setVentas(int ventas) {
        this.ventas = ventas;
    }
    
    
    
}
