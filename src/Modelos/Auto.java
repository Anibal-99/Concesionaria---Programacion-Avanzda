package Modelos;

public class Auto {
    int id;
    String modelo;
    float precio;
    String observacion;

    public Auto(){}
    public Auto(int id, String modelo, float precio, String observacion) {
        this.id = id;
        this.modelo = modelo;
        this.precio = precio;
        this.observacion = observacion;
    }

    public int getId() {
        return this.id;
    }

    public String getModelo() {
        return this.modelo;
    }

    public String getObservacion() {
        return this.observacion;
    }

    public float getPrecio() {
        return this.precio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }


}
