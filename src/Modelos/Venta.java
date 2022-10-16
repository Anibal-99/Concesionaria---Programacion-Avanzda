/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;
	
import java.util.Date;

/**
 *
 * @author Anibal-99
 */
public class Venta {
    Date fecha = new Date();
    Auto auto;
    Cliente cliente;
    Vendedor vendedor;
    float montoTotal;

    public Venta() {
    }

    public Venta(Auto auto, Cliente cliente, Vendedor vendedor, float montoTotal) {
        this.auto = auto;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.montoTotal = montoTotal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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
