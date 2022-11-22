/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author Anibal-99
 */
public class modelReporteRow {

    String modelo;
    float costo;
    float precio;
    float ganancia;
    int ventas;

    public modelReporteRow(String modelo, float costo, float precio, float ganancia, int ventas) {
        this.modelo = modelo;
        this.costo = costo;
        this.precio = precio;
        this.ganancia = ganancia;
        this.ventas = ventas;
    }

    public modelReporteRow() {
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
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
