package Modelos;

public class Auto {
    int id;
    Modelo modelo;
    float precio;
    String observacion;
    Color color;
    float costo;

    public Auto() {
    }


    public Auto(int id, Modelo modelo, float precio, float costo, String observacion, Color color) {
        this.id = id;
        this.modelo = modelo;
        this.precio = precio;
        this.observacion = observacion;
        this.color=color;
        this.costo=costo;
    }

    public int getId() {
        return this.id;
    }

    public Modelo getModelo() {
        return this.modelo;
    }

    public String getObservacion() {
        return this.observacion;
    }

    public float getPrecio() {
        return this.precio;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public String toString() {
        return this.modelo.getMarca().getName() + " " + this.modelo.getNombre() + " " + this.modelo.getAnio();
    }
}