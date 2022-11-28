package Modelos;

import java.util.Date;

/**
 *
 * @author Anibal-99
 */
public class Venta {

    int id;
    String fecha;
    Auto auto;
    Cliente cliente;
    Vendedor vendedor;
    float montoTotal;
    int cantidad;
    float impuesto;

    public Venta() {
    }

    public Venta(int id, String fecha, Auto auto, Cliente cliente, Vendedor vendedor, float montoTotal, int cantidad, float impuesto) {
        this.id = id;
        this.fecha = fecha;
        this.auto = auto;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.montoTotal = montoTotal;
        this.cantidad = cantidad;
        this.impuesto = impuesto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(float impuesto) {
        this.impuesto = impuesto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public float getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(float montoTotal) {
        this.montoTotal = montoTotal;
    }
}
